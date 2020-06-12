package com.chengqj.springbootsenior.tcp.connect;

import com.chengqj.springbootsenior.tcp.config.ConnectConfig;
import com.chengqj.springbootsenior.tcp.config.ReportConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @Author chengqj
 * @Date 2020/6/11 0011 23:31
 * @Desc
 */
public class BusinessManager {
    public static void main(String[] args) {
        ConnectConfig connectConfig = new ConnectConfig();
        connectConfig.setIp("218.17.122.52");
        connectConfig.setPort(32886);

        ReportConfig reportConfig = new ReportConfig();
        reportConfig.setBuildingNo("GW123");
        reportConfig.setCollectorNo("GW001");

        try {
            DataOperator dataOperator = DataOperatorBuilder.getDataOperator(connectConfig, reportConfig);
        } catch (IOException e) {
            //TODO 网络断开-重试
        }

        try {
            TimeUnit.MINUTES.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    // 发送数据


    // 历史数据查询
}
