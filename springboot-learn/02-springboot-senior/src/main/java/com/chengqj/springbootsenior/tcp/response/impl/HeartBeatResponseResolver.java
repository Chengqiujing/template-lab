package com.chengqj.springbootsenior.tcp.response.impl;

import com.chengqj.springbootsenior.tcp.response.Response;
import com.chengqj.springbootsenior.tcp.response.ResponseResolver;
import com.chengqj.springbootsenior.tcp.util.LogUtil;

/**
 * @Author chengqiujing
 * @Date 2020/6/15 0:18
 * @Desc
 */
public class HeartBeatResponseResolver implements ResponseResolver {
    @Override
    public void dealWith(Response response) {
        String time = response.getContentByPath("/root/heart_beat/time");
        if(time != null){
            LogUtil.LOGGER.info("心跳延时时间：" + time);
        }

    }
}
