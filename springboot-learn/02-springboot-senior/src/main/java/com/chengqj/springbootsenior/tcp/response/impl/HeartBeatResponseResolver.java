package com.chengqj.springbootsenior.tcp.response.impl;

import com.chengqj.springbootsenior.tcp.response.Response;
import com.chengqj.springbootsenior.tcp.response.ResponseResolver;

/**
 * @Author chengqiujing
 * @Date 2020/6/15 0:18
 * @Desc
 */
public class HeartBeatResponseResolver implements ResponseResolver {
    @Override
    public void dealWith(Response response) {
        System.out.println("延续了多长时间");
    }
}
