package com.ganwei.datapush.tcp.response;

import com.ganwei.datapush.tcp.operator.Operator;

/**
 * @Author chengqiujing
 * @Date 2020/6/14 15:18
 * @Desc
 */
public interface ResponseResolver {

    void dealWith(Response response, Operator operator);

}
