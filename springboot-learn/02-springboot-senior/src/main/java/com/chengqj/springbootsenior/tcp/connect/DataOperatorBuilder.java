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


}
