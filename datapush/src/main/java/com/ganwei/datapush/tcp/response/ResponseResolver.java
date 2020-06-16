package com.ganwei.datapush.tcp.response;

/**
 * @Author chengqiujing
 * @Date 2020/6/14 15:18
 * @Desc
 */
public interface ResponseResolver {

    void dealWith(Response response);

}
