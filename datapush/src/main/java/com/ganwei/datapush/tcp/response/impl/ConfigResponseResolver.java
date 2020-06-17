package com.ganwei.datapush.tcp.response.impl;

import com.ganwei.datapush.tcp.response.Response;
import com.ganwei.datapush.tcp.response.ResponseResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author chengqiujing
 * @Date 2020/6/17 9:37
 * @Desc
 */
public class ConfigResponseResolver implements ResponseResolver {
    private final Logger logger = LoggerFactory.getLogger(ConfigResponseResolver.class);
    @Override
    public void dealWith(Response response) {
        String contentByPath = response.getContentByPath("/root/config/period");
        if(contentByPath != null){
            Integer integer = Integer.valueOf(contentByPath);
            logger.info("数据发送频率改为："+integer+"分钟/次");
//            BusinessManager.dataPeriod = integer;
        }
    }
}
