package com.ganwei.datapush.tcp.service;


import com.ganwei.datapush.tcp.config.ConnectConfig;
import com.ganwei.datapush.tcp.config.ReportConfig;
import com.ganwei.datapush.tcp.connect.Connector;
import com.ganwei.datapush.tcp.connect.ConnectorFactory;
import com.ganwei.datapush.tcp.operator.DataOperatorBuilder;
import com.ganwei.datapush.tcp.operator.Operator;
import com.ganwei.datapush.tcp.report.Report;
import com.ganwei.datapush.tcp.report.ReportTypt;
import com.ganwei.datapush.tcp.response.ResponseResoleverHandler;
import com.ganwei.datapush.tcp.response.impl.ConfigResponseResolver;
import com.ganwei.datapush.tcp.response.impl.HeartBeatResponseResolver;
import com.ganwei.datapush.tcp.service.impl.PointDataServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author chengqiujing
 * @Date 2020/6/15 11:34
 * @Desc
 */
@Component
public class DataPushClient {

    private final Logger logger = LoggerFactory.getLogger(DataPushClient.class);
    
    private final static int FIXED_THREDS = 3; // 处理业务逻辑线程数量

    BusinessManager businessManager;

    public static Report report = null;

    @Autowired
    private ConnectConfig connectConfig;

    @Autowired
    private ReportConfig reportConfig;

    public void start(){
        while(true) {
//            ConnectConfig connectConfig = new ConnectConfig();
//            connectConfig.setIp("218.17.122.52");
//            connectConfig.setPort(32886);
//
//            ReportConfig reportConfig = new ReportConfig();
//            reportConfig.setBuildingNo("GW123");
//            reportConfig.setCollectorNo("GW001");

            Connector connector = ConnectorFactory.getTCPConnector(connectConfig);
            Operator dataOperator = DataOperatorBuilder.getDataOperator(connector);

            // 相应处理器
            ResponseResoleverHandler handler = new ResponseResoleverHandler(FIXED_THREDS);
            handler.register(ReportTypt.HEART_BEAT_TIME, new HeartBeatResponseResolver());  // 注册心跳响应处理
            handler.register(ReportTypt.CONFIG_PERIOD, new ConfigResponseResolver(reportConfig)); // 注册配置响应处理


            businessManager = new BusinessManager(dataOperator, true, reportConfig, handler, new PointDataServiceImpl());

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
            logger.info("客户端关闭...");
            businessManager.close();
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("客户端重启......");
        }
    }


    public static void main(String[] args) {
        DataPushClient client = new DataPushClient();
        client.start();
    }
}
