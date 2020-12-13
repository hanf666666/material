package com.it.Controller;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zjl
 * @create 2018-08-20 10:29
 **/
public class HttpClentPostTest2 {
    private RequestConfig.Builder custom;

    public static void main(String[] args) throws Exception {
        //1. ָ��url
        String urlStr = "http://www.baidu.com";

        //2. ��ȡ����,��������
        CloseableHttpClient httpClient = HttpClients.createDefault();//�൱����һ�������

        //2.1 ���� ����ʽ�Ķ���(httpPost)
        HttpPost httpPost = new HttpPost(urlStr);
        //��������ͷ������
        httpPost.setHeader("","");

        //2.2 ����������
        //���ó�ʱʱ��builder
        RequestConfig.Builder builder = RequestConfig.custom();
        builder.setConnectTimeout(20000);
        builder.setConnectTimeout(20000);
        RequestConfig config = builder.build();
        httpPost.setConfig(config);

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
