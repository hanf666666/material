package com.it.Controller;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zjl
 * @create 2018-08-20 10:29
 **/
public class HttpClentPostTest {
    public static void main(String[] args) throws Exception {
        //1. 指定url
        String urlStr = "http://www.baidu.com";

        //2. 获取数据,发送请求
        CloseableHttpClient httpClient = HttpClients.createDefault();//相当于是一个浏览器

        //2.1 创建 请求方式的对象(httpPost)
        HttpPost httpPost = new HttpPost(urlStr);

        //2.2 设置请求体
        List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
        list.add(new BasicNameValuePair("username","22222"));
        list.add(new BasicNameValuePair("age","38"));
        HttpEntity entity = new UrlEncodedFormEntity(list);
        httpPost.setEntity(entity);

        //3. 发送请求
        CloseableHttpResponse response = httpClient.execute(httpPost);

        //4. 获取html页面
        System.out.println("=======================");
        System.out.println(EntityUtils.toString(response.getEntity(),"utf-8"));
        System.out.println("=======================");
        //5. 关闭
        httpClient.close();
    }
}
