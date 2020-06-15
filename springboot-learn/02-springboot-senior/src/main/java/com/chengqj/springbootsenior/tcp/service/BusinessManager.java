package com.chengqj.springbootsenior.tcp.service;

import com.chengqj.springbootsenior.tcp.config.ReportConfig;
import com.chengqj.springbootsenior.tcp.entity.Meter;
import com.chengqj.springbootsenior.tcp.operator.Operator;
import com.chengqj.springbootsenior.tcp.report.Report;
import com.chengqj.springbootsenior.tcp.report.ReportFactory;
import com.chengqj.springbootsenior.tcp.response.Response;
import com.chengqj.springbootsenior.tcp.response.ResponseResoleverHandler;
import com.chengqj.springbootsenior.tcp.util.LogUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Author chengqj
 * @Date 2020/6/11 0011 23:31
 * @Desc
 */
@AllArgsConstructor
@Data
public class BusinessManager {

    private Operator operator;

    private volatile static int dataPeriod = 15; // 15分钟发送

    private volatile boolean runnig = true;

    private final static int PERIOD = 5; // 心跳间隔5分钟

    private ReportConfig reportConfig;

    private ResponseResoleverHandler handler;

    private final Map<String,Boolean> runStatus = new ConcurrentHashMap<>();

    /**
     * 心跳线程
     */
    public void heartBeat() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Report heartBeatReport = ReportFactory.getHeartBeatReport(reportConfig.getBuildingNo(), reportConfig.getCollectorNo());
                while(runnig) {
                    try {
                        operator.send(heartBeatReport.getReport());
                        runStatus.put("heartBeat",true);
                    } catch (IOException e) {
                        runnig = false;
                        runStatus.put("heartBeat",false);
                        LogUtil.LOGGER.error("心跳线程异常：心跳线程报错关闭",e);
                    }

                    try {
                        TimeUnit.MINUTES.sleep(PERIOD);
                    } catch (InterruptedException e) {
                        LogUtil.LOGGER.error("心跳线程睡眠被打断",e);
                    }

                }
                runStatus.put("heartBeat",false);
            }
        }, "Heart Beat");
        thread.start();
    }

    /**
     * 身份验证
     * @return
     */
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
            String result = md5Receive.getContentByPath("/root/id_validate/result");
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

    /**
     * 数据定时上传
     * @return
     */
    public void sendDataInterval(){
        Thread dataSender = new Thread(()->{
            Report dataReport = null;
            while(runnig){
                try {
                    dataReport = getDataReport();
                    operator.send(dataReport.getReport());
                    runStatus.put("dataInterval",true);
                } catch (IOException e) {
                    if (dataReport != null) {
                        DataPushClient.report = dataReport;
                    }
                    runnig = false;
                    runStatus.put("dataInterval",false);
                    LogUtil.LOGGER.error("数据上传线程异常：数据上传线程报错关闭",e);
                }
                try {
                    TimeUnit.MINUTES.sleep(dataPeriod);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            runStatus.put("dataInterval",false);
            LogUtil.LOGGER.info("定时发送数据线程（Data Send Inteval）关闭");
        },"Data Send Inteval");
        dataSender.start();
        LogUtil.LOGGER.info("定时发送数据线程（Data Send Inteval）启动");
    }

    /**
     * 返回结果处理
     */
    public void dealwithResponse(){
        Thread responseHandler = new Thread(()->{
            while(runnig){
                try {
                    Response receive = operator.receive();
                    handler.deal(receive);
                } catch (IOException e) {
                    runnig = false;
                    LogUtil.LOGGER.error("结果处理线程异常：结果处理线程报错关闭",e);
                }
            }
        },"responseHandler");
        responseHandler.start();
    }

    public void close(){
        runnig = false;
        int count = 0;
        Set<String> keySet = runStatus.keySet();

        for (String s : keySet) {
            while(count<5){
                if(true == runStatus.get(s)){
                    try {
                        LogUtil.LOGGER.error("有线程未结束任务，等待5s......");
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                count++;
            }
            count = 0;
        }
        operator.close();
    }

    private Report getDataReport(){

        List<Meter> list = null;

        if(DataPushClient.report != null){
            // TODO 取这个点到现在这个点的数据 service
        }else{
            list = new ArrayList<>();
        }

        Report dataReport = ReportFactory.getDataReport(reportConfig.getBuildingNo(),
                reportConfig.getCollectorNo(), "123", true,
                LocalDateTime.now(), list);
        return dataReport;
    }
}
