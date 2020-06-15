package com.chengqj.springbootsenior.tcp.response;

import com.chengqj.springbootsenior.tcp.util.LogUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author chengqiujing
 * @Date 2020/6/14 1:29
 * @Desc 归类返回报文，缓存，并处理
 */
public class ResponseResoleverHandler {
    private Map<String,ResponseResolver> handlerMap = new ConcurrentHashMap<>();

    private ExecutorService executorService;

    public ResponseResoleverHandler(int fixedThreds) {
        if(fixedThreds <1) fixedThreds = 1;
        this.executorService = Executors.newFixedThreadPool(fixedThreds);
    }

    public void register(String key, ResponseResolver resolver){
        if(key == null||resolver == null) return;
        handlerMap.put(key,resolver);
    }

    public void deal(Response response){
        executorService.submit(() -> {
            LogUtil.LOGGER.info("获得处理结果线程");
            String contentByPath = response.getContentByPath("/root/common/type");
            ResponseResolver responseResolver = handlerMap.get(contentByPath);
            if (responseResolver != null) {
                responseResolver.dealWith(response);
            } else {
                LogUtil.LOGGER.info("没有此报文的业务处理器");
            }
        });
    }

    public void remove(String key){
        if(handlerMap.containsKey(key)){
            handlerMap.remove(key);
        }
    }
}
