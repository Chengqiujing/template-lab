package com.chengqj.springbootsenior.tcp.service;

import com.chengqj.springbootsenior.tcp.config.ConnectConfig;
import com.chengqj.springbootsenior.tcp.config.ReportConfig;
import com.chengqj.springbootsenior.tcp.connect.Connector;
import com.chengqj.springbootsenior.tcp.connect.ConnectorFactory;
import com.chengqj.springbootsenior.tcp.operator.DataOperatorBuilder;
import com.chengqj.springbootsenior.tcp.operator.Operator;
import com.chengqj.springbootsenior.tcp.report.Report;
import com.chengqj.springbootsenior.tcp.report.ReportFactory;
import com.chengqj.springbootsenior.tcp.response.Response;
import com.chengqj.springbootsenior.tcp.util.LogUtil;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Author chengqj
 * @Date 2020/6/11 0011 23:31
 * @Desc
 */
@AllArgsConstructor
public class BusinessManager {

    private Operator operator;

    private final static int period = 5; // 心跳间隔5分钟

    private ReportConfig reportConfig;

    // 心跳
    public void heartBeat() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
//                    if(isStop){
//                        LogUtil.LOGGER.info("心跳线程停止...咚~咚...咚~.........");
//                        return;
//                    }

                    Report heartBeatReport = ReportFactory.getHeartBeatReport(reportConfig.getBuildingNo(), reportConfig.getCollectorNo());
                    operator.send(heartBeatReport.getReport());
                    try {
                        Response receive = operator.receive();
                        LogUtil.LOGGER.info("心跳延时报文\n"+receive.getText());
                    } catch (IOException e) {
                        e.printStackTrace();
                        LogUtil.LOGGER.warn("心跳延时信息接收失败",e);
                    }

                    try {
                        TimeUnit.SECONDS.sleep(period*3);
                    } catch (InterruptedException e) {
                        LogUtil.LOGGER.error("心跳线程睡眠被打断",e);
                    }
                }
            }
        }, "Heart Beat");
        thread.start();
    }

    // 身份验证
    public boolean validate() {
        try {
            LogUtil.LOGGER.info("》》》身份验证开始《《《");
            // 采集器编号，建筑编号是否为空
            if (reportConfig.getBuildingNo() == null || reportConfig.getCollectorNo() == null) {
                LogUtil.LOGGER.error("------>报文配置项为空（" + ReportConfig.class.getName() + "），身份无法验证<------");
                return false;
            }
            // 身份验证：获取sequence
            Report idValidateRequestReport = ReportFactory.getIdValidateRequestReport(reportConfig.getBuildingNo(), reportConfig.getCollectorNo());
            operator.send(idValidateRequestReport.getReport());
            Response receive = operator.receive();
            String sequence = receive.getContentByPath("/root/id_validate/sequence");
            if (Objects.isNull(sequence)) {
                LogUtil.LOGGER.error("身份验证异常：返回sequence为空");
                return false;
            }
            LogUtil.LOGGER.info("身份验证：sequence=" + sequence);

            // MD5再次验证
            Report idValidateMD5Report = ReportFactory
                    .getIdValidateMD5Report(reportConfig.getBuildingNo(), reportConfig.getCollectorNo(), sequence);
            operator.send(idValidateMD5Report.getReport());
            Response md5Receive = operator.receive();
            String result = md5Receive.getContentByPath("result");
            if ("pass".equals(result)) {
                LogUtil.LOGGER.error("》》》身份验证通过《《《");
                return true;
            } else {
                LogUtil.LOGGER.error("》》》身份验证失败：未通过《《《");
                return false;
            }
        } catch (IOException e) {
            LogUtil.LOGGER.error("身份验证异常：流错误", e);
        }
        return false;
    }


    public void sendDataTimune(){

    }


    public static void main(String[] args) {
        ConnectConfig connectConfig = new ConnectConfig();
        connectConfig.setIp("218.17.122.52");
        connectConfig.setPort(32886);

        ReportConfig reportConfig = new ReportConfig();
        reportConfig.setBuildingNo("GW123");
        reportConfig.setCollectorNo("GW001");

        Connector connector = ConnectorFactory.getTCPConnector(connectConfig);
        Operator dataOperator = DataOperatorBuilder.getDataOperator(connector);

        BusinessManager businessManager = new BusinessManager(dataOperator,reportConfig);

        if (businessManager.validate()) {
            businessManager.heartBeat(); // 心跳


        }




    }

}
