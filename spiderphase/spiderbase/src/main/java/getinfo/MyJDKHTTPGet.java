package getinfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class MyJDKHTTPGet {

    public static void main(String[] args) throws IOException {
        //请求路径 请求方式 请求参数 响应数据

        //1.创建url对象，指定请求路径
        URL url = new URL("https://www.baidu.com/");
        //2.开启连接
        URLConnection conn = url.openConnection();
        //3.发送请求，获取响应数据
        InputStream is = conn.getInputStream();
        //4.包装打印数据
        BufferedReader reader = new BufferedReader(new InputStreamReader(is,Charset.forName("utf-8")));

        String line = null;
        while ((line = reader.readLine())!= null ){
            System.out.println(line);
        }

        reader.close();
        is.close();
    }

}
