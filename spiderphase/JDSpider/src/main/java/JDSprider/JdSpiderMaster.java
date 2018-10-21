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
public class JdSpiderMaster {
    private static final Integer PAGE_NUM = 5;

    private static SqlSession sqlSession = null;
    private static int pageNum = 0;
    private static ExecutorService threadPool;
    private static JedisPool jedisPool = null;
    private static CountDownLatch downLatch = new CountDownLatch(PAGE_NUM-1);

    static {
        threadPool = Executors.newFixedThreadPool(10);
        jedisPool = new JedisPool(new JedisPoolConfig(), "192.168.15.3");
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("开始添加SKU到Redis");

        String startUrl = "https://search.jd.com/Search?keyword=%E5%B0%8F%E7%B1%B3&enc=utf-8&wq=%E5%B0%8F%E7%B1%B3&pvid=ca420f46012d48c880efd4f757a7a7b0";
        productSKU(startUrl);

        for (pageNum = 2; pageNum <= PAGE_NUM; pageNum++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        String pageUrl = "https://search.jd.com/Search?keyword=%E5%B0%8F%E7%B1%B3&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&suggest=2.def.0.V06&wq=xiao&page=" + pageNum + "&s=59&click=0";
                        productSKU(pageUrl);
                    }finally {
                        downLatch.countDown();
                    }
                }
            });
        }

        downLatch.await();
        System.out.println("sku添加完毕");
        System.exit(0);
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

            putIntoRedis(sku);
        }
    }

    public static void putIntoRedis(String sku) {
        Jedis jedis = jedisPool.getResource();

        jedis.lpush("com:wxy:jdspider:product:sku",sku);
        jedis.close();
    }
}
