package getinfo;

import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.nio.charset.Charset;

public class MyHTTPFluentAPI {
    public static void main(String[] args) throws IOException {
        String getReturn = Request.Get("https://www.baidu.com/").execute().returnContent().asString(Charset.forName("utf-8"));
        System.out.println(getReturn);
    }
}
