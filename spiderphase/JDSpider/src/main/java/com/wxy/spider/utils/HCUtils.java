package com.wxy.spider.utils;


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
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

public final class HCUtils {
    private static volatile HttpClient httpClient = null;

    public static HttpClient getHttpClient(){
        if (null == httpClient){
            synchronized (HCUtils.class){
                if (null == httpClient){
                    httpClient = HttpClients.createDefault();
                }
            }
        }
        return httpClient;
    }

    public static String HCDoPost(String url, Map<String, String> paramsMap, String charset) throws IOException {
        //1.创建HttpClient对象,单例的
        httpClient = getHttpClient();

        //2.请求准备
        HttpPost httpPost = new HttpPost(url);
        //post请求设置参数传入参数list
        List<NameValuePair> list = new ArrayList<>();
        //根据paramsMap添加参数
        Iterator<Map.Entry<String, String>> iterator = paramsMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> curEntry = iterator.next();
            list.add(new BasicNameValuePair(curEntry.getKey(), curEntry.getValue()));
        }
        //如果有参数则设置参数
        if (list.size() > 0) {
            httpPost.setEntity(new UrlEncodedFormEntity(list, charset));
        }

        //3.执行请求，获取返回对象
        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity resultEntity = response.getEntity();

        return EntityUtils.toString(resultEntity,Charset.forName(charset));
    }

    public static String HCDoGet(String url,String...charset) throws IOException {
        if(charset == null || charset.length < 1){
            charset = new String[]{"utf-8"};
        }

        //1.创建HttpClient对象
        httpClient = getHttpClient();
        //2.请求准备
        HttpGet httpGet = new HttpGet(url);
        //3.执行请求，获取返回对象
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity resultEntity = null;
        if (response != null && response.getStatusLine().getStatusCode() == 200) {
            resultEntity = response.getEntity();
        }
        return EntityUtils.toString(resultEntity,charset[0]);
    }

    @Test
    public void testHCDoPost(){
        String url = "https://www.zhihu.com/explore/recommendations";
        Map<String, String> paramsMap = new HashMap<>();
        String charset = "utf-8";
        try {
            String resultStr = HCUtils.HCDoGet(url,"utf-8");
            System.out.println(resultStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
