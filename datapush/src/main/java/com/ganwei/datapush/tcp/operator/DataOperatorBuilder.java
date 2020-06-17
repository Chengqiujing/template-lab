package com.ganwei.datapush.tcp.operator;


import com.ganwei.datapush.tcp.connect.Connector;
import com.ganwei.datapush.tcp.encrypt.AESEncryptor;

public class DataOperatorBuilder {

    public static Operator getDataOperator(Connector connector) {
        DataOperator dataOperator = new DataOperator(connector, new AESEncryptor());

        return dataOperator;
    }


}
