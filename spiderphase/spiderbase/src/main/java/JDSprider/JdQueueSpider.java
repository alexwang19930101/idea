package JDSprider;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wxy.QueueTest.bean.Product;
import com.wxy.QueueTest.dao.ProductMapper;
import org.apache.ibatis.session.SqlSession;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import com.wxy.spider.utils.HCUtils;
import com.wxy.spider.utils.MybatisUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;


/**
 * 在本代码中使用了闭锁来完成等待线程池中线程执行完毕
 * 这完全是为了测试闭锁
 * 时间应该把消费的代码提前到准备之前开始执行
 */
// url = https://search.jd.com/Search?keyword=%E5%B0%8F%E7%B1%B3&enc=utf-8&wq=%E5%B0%8F%E7%B1%B3&pvid=ca420f46012d48c880efd4f757a7a7b0
public class JdQueueSpider {
    private static CountDownLatch endGate = new CountDownLatch(9);
    private static SqlSession sqlSession = null;
    private static ArrayBlockingQueue<String> SKUBlockingQueue = new ArrayBlockingQueue<String>(1000);
    private static int pageNum = 0;
    private static ExecutorService threadPool;
    private static volatile int total = 300;

    static {
        sqlSession = MybatisUtils.getSqlSession();

        threadPool = Executors.newFixedThreadPool(14);
    }

    public static void main(String[] args) throws InterruptedException {
        String startUrl = "https://search.jd.com/Search?keyword=%E5%B0%8F%E7%B1%B3&enc=utf-8&wq=%E5%B0%8F%E7%B1%B3&pvid=ca420f46012d48c880efd4f757a7a7b0";
        productSKU(startUrl);

        for (pageNum = 2; pageNum <= 10; pageNum++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        String pageUrl = "https://search.jd.com/Search?keyword=%E5%B0%8F%E7%B1%B3&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&suggest=2.def.0.V06&wq=xiao&page=" + pageNum + "&s=59&click=0";
                        productSKU(pageUrl);
                    } finally {
                        endGate.countDown();
                    }
                }
            });
        }

        //闭锁等待
        endGate.await();

        System.out.println(SKUBlockingQueue.size());
        for (int i = 0; i < 10; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        Product productR = parseJdInfoData();
                        insert2MySql(productR);
                    }
                }
            });
        }


        while (true) {
            System.out.println("剩余数量：" + SKUBlockingQueue.size());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void productSKU(String url) {
        String htmlStr = null;
        try {
            htmlStr = HCUtils.HCDoGet(url, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Document htmlDoc = Jsoup.parse(htmlStr);
        parseJdListData(htmlDoc);
    }

    private static void parseJdListData(Document document) {
        Elements goodList = document.select("#J_goodsList").select(".gl-item");
        String sku = null;
        String spu = null;
        for (Element good : goodList) {
            spu = good.attr("data-spu");
            sku = good.attr("data-sku");

            try {
                SKUBlockingQueue.put(sku);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static Product parseJdInfoData() {
        String sku = null;
        try {
            sku = SKUBlockingQueue.take();
            System.out.println(sku + " total:" + total--);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Product product = new Product();
        String href = "https://item.jd.com/" + sku + ".html";
        System.out.println(href);

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
            JSONArray priceJsonArray = (JSONArray) JSONArray.parse(pirceJsonArrayStr);
            JSONObject priceJson = (JSONObject) priceJsonArray.get(0);
            String price = (String) priceJson.get("p");
            product.setPrice(Double.parseDouble(price));
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
//        insert2MySql(product);
//        System.out.println(product);
//        System.out.println("------------");
        return product;
    }

    private static void insert2MySql(Product product) {
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);

        productMapper.insertSelective(product);
        sqlSession.commit();
    }


    private static void cleanProduct(Product product) {
        product.setTitle(null);
        product.setAd(null);
        product.setPrice(null);
        product.setBranch(null);
        product.setDescibe(null);
        product.setId(null);
    }

    @Test
    public void testFastJsonTransMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", "b");
        String jsonStr = JSONObject.toJSONString(map);
        System.out.println(jsonStr);
    }
}
