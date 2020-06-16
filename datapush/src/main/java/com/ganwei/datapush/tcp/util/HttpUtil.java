package com.ganwei.datapush.tcp.util;

import com.ganwei.datapush.tcp.entity.HttpModel;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @Author chengqiujing
 * @Date 2020/6/16 16:10
 * @Desc
 */
public class HttpUtil {

    public static HttpModel post(String url, String dataJsonString){
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Post请求
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(dataJsonString, "UTF-8");
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");

        HttpModel model = new HttpModel();
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();

            int statusCode = response.getStatusLine().getStatusCode();
            model.setStatus(statusCode);
            if (responseEntity != null) {
                model.setData(EntityUtils.toString(responseEntity));
            }
            return model;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }
}
