package parsedata;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MyJsoup {
    public static void main(String[] args) throws IOException {
        //jsoup获取docuemnt
        Document doc = Jsoup.parse(new URL("http://www.itcast.cn"),2000);
        //获取 标签
        Elements aTag = doc.select("a");
        //获取 id
        Elements webimID = doc.select("#webim");
        //获取 class
        Elements  footerClass = doc.select(".footer");
        //获取某个属性的
        Elements srcAttr = doc.select("[src]");
        //获取属性以什么开头的
        Elements sStartAttr = doc.select("[^s]");
        // 获取某个属性值等于什么的
        Elements jslist = doc.select("[src=/js/index.js]");
        // [attr^=value] 开始，[attr$=value] 结束，[attr*=value] 包含


    }
}
