package com.chengqj.springbootsenior.tcp.service;

import com.chengqj.springbootsenior.tcp.config.ConnectConfig;
import com.chengqj.springbootsenior.tcp.config.ReportConfig;
import com.chengqj.springbootsenior.tcp.connect.Connector;
import com.chengqj.springbootsenior.tcp.connect.ConnectorFactory;
import com.chengqj.springbootsenior.tcp.operator.DataOperatorBuilder;
import com.chengqj.springbootsenior.tcp.operator.Operator;
import com.chengqj.springbootsenior.tcp.report.Report;
import com.chengqj.springbootsenior.tcp.report.ReportTypt;
import com.chengqj.springbootsenior.tcp.response.ResponseResoleverHandler;
import com.chengqj.springbootsenior.tcp.response.impl.HeartBeatResponseResolver;
import com.chengqj.springbootsenior.tcp.util.LogUtil;

import java.util.concurrent.TimeUnit;

/**
 * @Author chengqiujing
 * @Date 2020/6/15 11:34
 * @Desc
 */
public class DataPushClient {

    private final static int FIXED_THREDS = 3; // 处理业务逻辑线程数量

    BusinessManager businessManager;

    public static Report report = null;

    public void start(){
        while(true) {
            ConnectConfig connectConfig = new ConnectConfig();
            connectConfig.setIp("218.17.122.52");
            connectConfig.setPort(32886);

            ReportConfig reportConfig = new ReportConfig();
            reportConfig.setBuildingNo("GW123");
            reportConfig.setCollectorNo("GW001");

            Connector connector = ConnectorFactory.getTCPConnector(connectConfig);
            Operator dataOperator = DataOperatorBuilder.getDataOperator(connector);

            ResponseResoleverHandler handler = new ResponseResoleverHandler(FIXED_THREDS);
            handler.register(ReportTypt.HEART_BEAT_TIME, new HeartBeatResponseResolver());

            businessManager = new BusinessManager(dataOperator, true, reportConfig, handler);

            if (businessManager.validate()) {
                businessManager.heartBeat(); // 心跳
                businessManager.sendDataInterval();
                businessManager.dealwithResponse();
            }
            while(businessManager.isRunnig()){
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            LogUtil.LOGGER.info("客户端关闭...");
            businessManager.close();
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LogUtil.LOGGER.info("客户端重启......");
        }
    }


    public static void main(String[] args) {
        DataPushClient client = new DataPushClient();
        client.start();
    }
}
