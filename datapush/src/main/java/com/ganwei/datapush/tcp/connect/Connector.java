package com.ganwei.datapush.tcp.connect;


import com.ganwei.datapush.tcp.config.ConnectConfig;

import java.io.IOException;

/**
 * @Author chengqiujing
 * @Date 2020/6/11 14:44
 * @Desc
 */
public interface Connector {

    void init(ConnectConfig config) throws IOException;

    void send(byte[] bytes) throws IOException;

    int receive(byte[] bytes) throws IOException;

    void close() throws IOException;
}
