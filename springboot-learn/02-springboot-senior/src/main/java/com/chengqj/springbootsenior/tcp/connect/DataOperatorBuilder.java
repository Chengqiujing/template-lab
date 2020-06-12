package com.chengqj.springbootsenior.tcp.connect;

import com.chengqj.springbootsenior.tcp.config.ConnectConfig;
import com.chengqj.springbootsenior.tcp.encrypt.AESEncryptor;

public class DataOperatorBuilder {

    public static DataOperator getDataOperator(ConnectConfig config){
        Connector connector = ConnectorFactory.getConnector(config, ConnectType.TCP);
        DataOperator dataOperator = new DataOperator(connector,new AESEncryptor());

        // 身份验证

        // 心跳启动

        return dataOperator;
    }

    // 心跳
    private static void heartBeat(){

    }

    // 身份验证
    private static boolean validate(DataOperator dataOperator){
        // 获取报文

        // 请求验证

        // 返回报文

        // 再次验证

        return false;
    }

}
