package com.chengqj.springbootsenior.tcp.report.impl;

import com.chengqj.springbootsenior.tcp.report.AbstractReport;
import com.chengqj.springbootsenior.tcp.report.ReportTypt;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author chengqiujing
 * @Date 2020/6/12 11:38
 * @Desc
 */
@Data
@AllArgsConstructor
public class HeartBeatReport extends AbstractReport {

    private String buildingNo;

    private String collectorNo;

    @Override
    protected String generateBuildingNo() {
        return buildingNo;
    }

    @Override
    protected String generateCollectorNo() {
        return collectorNo;
    }

    @Override
    protected String generateType() {
        return ReportTypt.HEART_BEAT_NOTIFY;
    }

    @Override
    protected String generateBzReport() {

        return "  <heart_beat operation=\"notify\"/>";
    }
}
