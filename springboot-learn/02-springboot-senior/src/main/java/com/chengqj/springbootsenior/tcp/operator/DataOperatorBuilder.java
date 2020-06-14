package com.chengqj.springbootsenior.tcp.operator;

import com.chengqj.springbootsenior.tcp.connect.Connector;
import com.chengqj.springbootsenior.tcp.encrypt.AESEncryptor;

public class DataOperatorBuilder {

    public static Operator getDataOperator(Connector connector) {
        DataOperator dataOperator = new DataOperator(connector, new AESEncryptor());

//        // 身份验证
//        boolean validate = dataOperator.validate(reportConfig);
//        if(validate){
//            // 心跳启动
//            dataOperator.heartBeat(reportConfig);
//        }

        return dataOperator;
    }


}
