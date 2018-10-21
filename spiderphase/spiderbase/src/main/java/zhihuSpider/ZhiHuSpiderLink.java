package zhihuSpider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.wxy.spider.utils.HCUtils;

import java.io.IOException;

public class ZhiHuSpiderLink {

    public static void main(String[] args) throws IOException {
        String url = "https://www.zhihu.com/explore/recommendations";
//        Document document = Jsoup.parse(new URL(url), 4000);
//        Document document = Jsoup.connect(url).userAgent("Mozilla/5.0 (iPad; U; CPU OS 4_3_3 like Mac OS X; en-us) " +
//                "AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5").get();
        Document document = Jsoup.parse(HCUtils.HCDoGet(url,"utf-8"));
//        System.out.println( document);

        Elements elements = document.select("div.zm-item");
        String title = null;
        String href = null;
        String answer = null;
        String author = null;
        for (Element element : elements) {

            title = element.select("div.zm-item").select("h2").
                    select("a").get(0).text();

            href = element.select("div.zm-item").select("h2").
                    select("a").attr("href");
            if (!href.contains("https://")) {
                href = "https://www.zhihu.com" + url;
            }

            answer = element.select("div.zm-item div.zh-summary").get(0).text();

            Elements elementAuthor = element.select("div.zm-item span.author-link-line");
            if (elementAuthor.size() > 0){
                author = elementAuthor .get(0).text();
            }else {
                author = "";
            }

            System.out.println("标题： " + title);
            System.out.println("url: " + href);
            System.out.println("简要回答: " + answer);
            System.out.println("author: " + author);
            System.out.println("----------");
        }
    }
}
