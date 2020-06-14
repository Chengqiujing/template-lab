package com.chengqj.springbootsenior.tcp.report;

import com.chengqj.springbootsenior.tcp.entity.Meter;
import com.chengqj.springbootsenior.tcp.report.impl.DataReport;
import com.chengqj.springbootsenior.tcp.report.impl.HeartBeatReport;
import com.chengqj.springbootsenior.tcp.report.impl.ValidateMD5Report;
import com.chengqj.springbootsenior.tcp.report.impl.ValidateRequestReport;

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
}
