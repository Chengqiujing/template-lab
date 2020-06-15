package com.chengqj.springbootsenior.tcp.response;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author chengqiujing
 * @Date 2020/6/14 1:29
 * @Desc 归类返回报文，缓存，并处理
 */
public class ResponseResoleverHandler {
    Map<String,ResponseResolver> handlerMap = new ConcurrentHashMap<>();

    public void register(String key,ResponseResolver resolver){
        if(key == null||resolver == null) return;
        handlerMap.put(key,resolver);
    }

    public void deal(Response response){
        String contentByPath = response.getContentByPath("/root/common/type");
        ResponseResolver responseResolver = handlerMap.get(contentByPath);
        responseResolver.dealWith(response);
    }

}
