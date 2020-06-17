package com.ganwei.datapush.tcp.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author chengqiujing
 * @Date 2020/6/9 11:10
 * @Desc
 */
@Component
@Data
public class ConnectConfig {
    @Value("{$weijing.conn.ip}")
    private String ip;

    @Value("{$weijing.conn.port}")
    private int port;
}
