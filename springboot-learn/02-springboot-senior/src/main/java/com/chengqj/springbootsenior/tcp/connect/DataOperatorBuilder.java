package com.chengqj.springbootsenior.tcp.connect;

import com.chengqj.springbootsenior.tcp.config.ConnectConfig;
import com.chengqj.springbootsenior.tcp.config.ReportConfig;
import com.chengqj.springbootsenior.tcp.encrypt.AESEncryptor;

import java.io.IOException;

public class DataOperatorBuilder {

    public static DataOperator getDataOperator(ConnectConfig connectConfig, ReportConfig reportConfig) throws IOException {
        Connector connector = ConnectorFactory.getConnector(connectConfig, ConnectType.TCP);
        DataOperator dataOperator = new DataOperator(connector, new AESEncryptor());

        // 身份验证
        boolean validate = dataOperator.validate(reportConfig);
        if(validate){
            // 心跳启动
            dataOperator.heartBeat(reportConfig);
        }

        return dataOperator;
    }


}
