package com.chengqj.springbootsenior.tcp.connect;

import com.chengqj.springbootsenior.exceptionhandler.GlobalExceptionHandler;
import com.chengqj.springbootsenior.tcp.config.ConnectConfig;
import com.chengqj.springbootsenior.tcp.connect.impl.TCPConnectiorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @Author chengqiujing
 * @Date 2020/6/11 14:48
 * @Desc
 */
public class ConnectorFactory {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private final static int RETRY_COUNT = 3;

    public static Connector getTCPConnector(ConnectConfig config) {
        TCPConnectiorImpl tcpConnectior = new TCPConnectiorImpl();
        try {
            tcpConnectior.init(config);
            return tcpConnectior;
        } catch (IOException e) {
            int count = 1;
            while(count <= RETRY_COUNT){
                logger.info("连接初始化错误，正在重试第"+count+"次");
                try {
                    tcpConnectior.close();
                    tcpConnectior.init(config);
                    TimeUnit.SECONDS.sleep(10);
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
            logger.info("无法与建立连接--"+config.toString());
        }
        return null;
    }
}
