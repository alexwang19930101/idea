package getinfo;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

public class MyHttpClient {
    public static void main(String[] args) throws Exception {
        //1.获取httpClient对象
        HttpClient httpClient = HttpClients.createDefault();
        //2.设置http 请求方式 请求路径
        HttpGet httpget = new HttpGet("https://www.baidu.com/");
        //3.通过httpClient对象执行步骤2定义的请求内容，得到返回对象
        HttpResponse response = httpClient.execute(httpget);
        //4.由返回对象执行处理
        HttpEntity entity = response.getEntity();//entity是获取的响应内容

        if (entity != null) {
            System.out.println("Login form get: " + response.getStatusLine());
            //打印响应内容
            System.out.println(EntityUtils.toString(entity,"utf-8"));
        }

        System.out.println("------------------");
        HttpPost httpost = new HttpPost("https://www.baidu.com/");

        List <NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("username", "username"));
        nvps.add(new BasicNameValuePair("password", "password"));

        httpost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

        response = httpClient.execute(httpost);
        entity = response.getEntity();

        System.out.println("Login form post: " + response.getStatusLine());
        Header[] headers = response.getAllHeaders();
        if (entity != null) {
            for (int i = 0; i < headers.length; i++) {
                System.out.println(headers[i].getName()+" "+headers[i].getValue());
            }
        }
    }
}
