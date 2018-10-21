package JDSprider;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wxy.spider.bean.Product;
import com.wxy.spider.dao.ProductMapper;
import com.wxy.spider.utils.HCUtils;
import com.wxy.spider.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


// url = https://search.jd.com/Search?keyword=%E5%B0%8F%E7%B1%B3&enc=utf-8&wq=%E5%B0%8F%E7%B1%B3&pvid=ca420f46012d48c880efd4f757a7a7b0
public class JdSpiderSlave {
    private static ExecutorService threadPool;
    private static JedisPool jedisPool = null;
    private static CountDownLatch downLatch = new CountDownLatch(10);
    private static ArrayBlockingQueue<String> skuBlockingQueue = new ArrayBlockingQueue<String>(10);

    static {
        threadPool = Executors.newFixedThreadPool(10);
        jedisPool = new JedisPool(new JedisPoolConfig(), "192.168.15.3");
    }

    public static void main(String[] args) throws InterruptedException {
        int numTotalAdd = 0;
        final int[] num = {numTotalAdd};

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        getSKUFromJedis();
                    } catch (Exception ex) {
                        break;
                    }
                }
            }
        }).start();

        Thread.sleep(200);
        for (int i = 0; i < 10; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    Product productR = new Product();
                    int flag = 0;
                    while (flag != 10) {
                        cleanProduct(productR);
                        if (skuBlockingQueue.size() != 0) {
                            productR = parseJdInfoData();

                            System.out.println("get product:" + productR);
                            insert2MySql(productR);
                            System.out.println("加入数据库完毕！" + (++num[0]));
                        } else {
                            flag++;
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    downLatch.countDown();
                }
            });
        }

        downLatch.await();
        System.out.println("信息解析完成！");
        System.exit(0);
    }

    private static Product parseJdInfoData() {
        String sku = null;
        try {
            sku = skuBlockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Product product = new Product();
        String href = "https://item.jd.com/" + sku + ".html";

        Document document = null;
        try {
            document = Jsoup.parse(HCUtils.HCDoGet(href, "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String title = document.select(".sku-name").get(0).text();
        product.setTitle(title);

        String brand = document.select("div.tab-con ul[id=parameter-brand] a").get(0).text();
        product.setBranch(brand);

        Elements infoList = document.select("div.tab-con ul[class=parameter2 p-parameter-list] li");
        Map<String, String> productInfo = new HashMap<>();
        for (Element info : infoList) {
            String infoAttrStr = info.text();
            productInfo.put(infoAttrStr.split("：")[0], infoAttrStr.split("：")[1]);
        }
        String productInfoJsonStr = JSONObject.toJSONString(productInfo);
        product.setDescibe(productInfoJsonStr);

        //处理价格
        //  https://p.3.cn/prices/mgets?callback=jQuery2913180&type=1&area=1_72_2799_0&pdtk=&pduid=1930835135&pdpin=&pin=null&pdbp=0&skuIds=J_7437788,J_32953185394,J_32868858095,J_6027746,J_33239063849,J_33341525798,J_30639439257,J_6984707,J_5524603,J_5338456&ext=11100000&source=item-pc
        //https://p.3.cn/prices/mgets?skuIds=J_7437788
        String priceUrl = "https://p.3.cn/prices/mgets?skuIds=J_" + sku;
        String pirceJsonArrayStr = null;
        try {
            pirceJsonArrayStr = HCUtils.HCDoGet(priceUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (pirceJsonArrayStr != null) {
            JSONObject priceJson = null;
            try {
                JSONArray priceJsonArray = (JSONArray) JSONArray.parse(pirceJsonArrayStr);
                priceJson = (JSONObject) priceJsonArray.get(0);
            } catch (ClassCastException ex) {
                priceJson = (JSONObject) JSONArray.parse(pirceJsonArrayStr);
            }
            if (priceJson != null) {
                String price = (String) priceJson.get("p");
                try {
                    product.setPrice(Double.parseDouble(price));
                } catch (NullPointerException ex) {
                    product.setPrice(null);
                }
            } else {
                product.setPrice(null);
            }
        }


        //处理异步请求  ads
        //  https://cd.jd.com/promotion/v2?skuId=31176857887&area=1_72_2799_0&cat=9987%2C653%2C655
        String adsUrl = "https://cd.jd.com/promotion/v2?skuId=" + sku + "&area=1_72_2799_0&cat=9987%2C653%2C655";
        String adsJsonStr = null;
        try {
            adsJsonStr = HCUtils.HCDoGet(adsUrl, "gbk");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (adsJsonStr != null) {
            JSONObject adsJson = JSONObject.parseObject(adsJsonStr);
            JSONObject adsJsonInfo = (JSONObject) adsJson.getJSONArray("ads").get(0);
            String ad = (String) adsJsonInfo.get("ad");
            if (ad.length() > 240) {
                ad = ad.substring(0, 240);
            }
            product.setAd(ad);
        }
        System.out.println(href);
        return product;
    }


    private static void getSKUFromJedis() {
        Jedis jedis = jedisPool.getResource();

        String sku = jedis.rpop("com:wxy:jdspider:product:sku");
        System.out.println(sku);
        try {
            skuBlockingQueue.put(sku);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    private static void insert2MySql(Product product) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);

        productMapper.insertSelective(product);
        sqlSession.commit();
        sqlSession.close();
    }

    private static void cleanProduct(Product product) {
        product.setTitle(null);
        product.setAd(null);
        product.setPrice(null);
        product.setBranch(null);
        product.setDescibe(null);
        product.setId(null);
    }
}
