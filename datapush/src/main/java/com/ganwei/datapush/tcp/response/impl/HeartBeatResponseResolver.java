package com.ganwei.datapush.tcp.response.impl;


import com.ganwei.datapush.tcp.response.Response;
import com.ganwei.datapush.tcp.response.ResponseResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author chengqiujing
 * @Date 2020/6/15 0:18
 * @Desc
 */
public class HeartBeatResponseResolver implements ResponseResolver {
    private final Logger logger = LoggerFactory.getLogger(HeartBeatResponseResolver.class);
    @Override
    public void dealWith(Response response) {
        String time = response.getContentByPath("/root/heart_beat/time");
        if(time != null){
            logger.info("心跳延时时间：" + time);
        }

    }
}
