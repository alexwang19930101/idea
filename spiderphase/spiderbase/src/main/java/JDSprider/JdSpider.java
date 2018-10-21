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

// url = https://search.jd.com/Search?keyword=%E5%B0%8F%E7%B1%B3&enc=utf-8&wq=%E5%B0%8F%E7%B1%B3&pvid=ca420f46012d48c880efd4f757a7a7b0
public class JdSpider {

    private static SqlSession sqlSession = null;
    static {
        sqlSession = MybatisUtils.getSqlSession();
    }

    public static void main(String[] args) {
        String startUrl = "https://search.jd.com/Search?keyword=%E5%B0%8F%E7%B1%B3&enc=utf-8&wq=%E5%B0%8F%E7%B1%B3&pvid=ca420f46012d48c880efd4f757a7a7b0";
        productSKU(startUrl);

        String pageUrl;
        for (int pageNum = 2;pageNum<=10;pageNum++){
            pageUrl ="https://search.jd.com/Search?keyword=%E5%B0%8F%E7%B1%B3&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&suggest=2.def.0.V06&wq=xiao&page="+pageNum+"&s=59&click=0";
            productSKU(pageUrl);
        }
    }

    public static void productSKU(String url){
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
        String href = null;
        String sku = null;
        String spu = null;
        for (Element good : goodList) {
            spu = good.attr("data-spu");
            sku = good.attr("data-sku");

            //直接使用会有重定向问题，找规律后可以sku来访问
//            href = good.select("div.p-img a[title]").get(0).attr("href");
            href = "https://item.jd.com/"+sku+".html";

            Document infoDataHtml = null;
            try {
                infoDataHtml = Jsoup.parse(HCUtils.HCDoGet(href,"utf-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (infoDataHtml != null){
                parseJdInfoData(sku);
            }
        }
    }

    private static void parseJdInfoData(String sku) {
        Product product = new Product();
        String href = "https://item.jd.com/"+sku+".html";

        Document document = null;
        try {
            document = Jsoup.parse(HCUtils.HCDoGet(href,"utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String title =  document.select(".sku-name").get(0).text();
        product.setTitle(title);

        String brand = document.select("div.tab-con ul[id=parameter-brand] a").get(0).text();
        product.setBranch(brand);

        Elements infoList = document.select("div.tab-con ul[class=parameter2 p-parameter-list] li");
//        System.out.println("商品详情：");
        Map<String,String> productInfo = new HashMap<>();
        for (Element info : infoList){
            String infoAttrStr = info.text();
            productInfo.put(infoAttrStr.split("：")[0],infoAttrStr.split("：")[1]);
        }
        String productInfoJsonStr = JSONObject.toJSONString(productInfo);
        product.setDescibe(productInfoJsonStr);
//        System.out.println(productInfoJsonStr);

        //处理价格
        //  https://p.3.cn/prices/mgets?callback=jQuery2913180&type=1&area=1_72_2799_0&pdtk=&pduid=1930835135&pdpin=&pin=null&pdbp=0&skuIds=J_7437788,J_32953185394,J_32868858095,J_6027746,J_33239063849,J_33341525798,J_30639439257,J_6984707,J_5524603,J_5338456&ext=11100000&source=item-pc
        //https://p.3.cn/prices/mgets?skuIds=J_7437788
        String priceUrl = "https://p.3.cn/prices/mgets?skuIds=J_"+sku;
        String pirceJsonArrayStr = null;
        try {
            pirceJsonArrayStr = HCUtils.HCDoGet(priceUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (pirceJsonArrayStr != null){
            JSONArray  priceJsonArray = (JSONArray) JSONArray.parse(pirceJsonArrayStr);
            JSONObject priceJson = (JSONObject) priceJsonArray.get(0);
            String price = (String) priceJson.get("p");
            product.setPrice(Double.parseDouble(price));
//            System.out.println("价格："+price);
        }


        //处理异步请求  ads
        //  https://cd.jd.com/promotion/v2?skuId=31176857887&area=1_72_2799_0&cat=9987%2C653%2C655
        String adsUrl = "https://cd.jd.com/promotion/v2?skuId="+sku+"&area=1_72_2799_0&cat=9987%2C653%2C655";
        String adsJsonStr = null;
        try {
            adsJsonStr = HCUtils.HCDoGet(adsUrl,"gbk");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (adsJsonStr != null){
            JSONObject  adsJson = JSONObject.parseObject(adsJsonStr);
            JSONObject adsJsonInfo = (JSONObject) adsJson.getJSONArray("ads").get(0);
            String ad = (String) adsJsonInfo.get("ad");
            if (ad.length() > 240){
                ad = ad.substring(0,240);
            }
            product.setAd(ad);
//            System.out.println("广告："+ad);
        }
//        insert2MySql(product);
        System.out.println(product);
        System.out.println("------------");
    }

    private static void insert2MySql(Product product){
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);

        productMapper.insertSelective(product);
        sqlSession.commit();
    }



    private static void cleanProduct(Product product){
        product.setTitle(null);
        product.setAd(null);
        product.setPrice(null);
        product.setBranch(null);
        product.setDescibe(null);
        product.setId(null);
    }
    @Test
    public void testFastJsonTransMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("a","b");
        String jsonStr = JSONObject.toJSONString(map);
        System.out.println(jsonStr);
    }
}
