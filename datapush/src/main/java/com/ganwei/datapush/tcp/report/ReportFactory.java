package com.ganwei.datapush.tcp.report;


import com.ganwei.datapush.tcp.entity.Meter;
import com.ganwei.datapush.tcp.report.impl.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author chengqiujing
 * @Date 2020/6/12 10:12
 * @Desc
 */
public class ReportFactory {


    private ReportFactory() {
    }

    /**
     * 获取身份验证请求报文
     *
     * @param buildingNo
     * @param collectorNo
     * @return
     */
    public static Report getIdValidateRequestReport(String buildingNo, String collectorNo) {
        return new ValidateRequestReport(buildingNo, collectorNo);
    }

    /**
     * 获取身份验证MD5验证报文
     *
     * @param buildingNo
     * @param collectorNo
     * @param sequence
     * @return
     */
    public static Report getIdValidateMD5Report(String buildingNo, String collectorNo, String sequence) {
        return new ValidateMD5Report(buildingNo, collectorNo, sequence);
    }

    /**
     * 获取心跳报文
     * @param buildingNo
     * @param collectorNo
     * @return
     */
    public static Report getHeartBeatReport(String buildingNo, String collectorNo) {
        return new HeartBeatReport(buildingNo, collectorNo);
    }

    /**
     * 获取定时上传数据的报文
     * @param buildingNo
     * @param collectorNo
     * @param dataSequence
     * @param parse
     * @param dateTime
     * @param meters
     * @return
     */
    public static Report getDataReport(String buildingNo, String collectorNo, String dataSequence, boolean parse, LocalDateTime dateTime, List<Meter> meters) {
        return new DataReport(buildingNo, collectorNo, dataSequence, parse, dateTime, meters);
    }

    /**
     * 配置报文
     * @param buildingNo
     * @param collectorNo
     * @return
     */
    public static Report getConfigAckReport(String buildingNo, String collectorNo){
        return new ConfigAckReport(buildingNo,collectorNo);
    }
}
