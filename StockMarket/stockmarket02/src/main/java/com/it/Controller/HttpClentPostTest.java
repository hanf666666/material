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
        //1. ָ��url
        String urlStr = "http://www.baidu.com";

        //2. ��ȡ����,��������
        CloseableHttpClient httpClient = HttpClients.createDefault();//�൱����һ�������

        //2.1 ���� ����ʽ�Ķ���(httpPost)
        HttpPost httpPost = new HttpPost(urlStr);

        //2.2 ����������
        List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
        list.add(new BasicNameValuePair("username","22222"));
        list.add(new BasicNameValuePair("age","38"));
        HttpEntity entity = new UrlEncodedFormEntity(list);
        httpPost.setEntity(entity);

        //3. ��������
        CloseableHttpResponse response = httpClient.execute(httpPost);

        //4. ��ȡhtmlҳ��
        System.out.println("=======================");
        System.out.println(EntityUtils.toString(response.getEntity(),"utf-8"));
        System.out.println("=======================");
        //5. �ر�
        httpClient.close();
    }
}
