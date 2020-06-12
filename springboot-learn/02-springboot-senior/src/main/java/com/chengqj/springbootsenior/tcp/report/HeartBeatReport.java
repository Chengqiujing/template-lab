package com.chengqj.springbootsenior.tcp.report;

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
    String generateBuildingNo() {
        return buildingNo;
    }

    @Override
    String generateCollectorNo() {
        return collectorNo;
    }

    @Override
    String generateType() {
        return ReportTypt.HEART_BEAT_NOTIFY;
    }

    @Override
    String generateBzReport() {

        return "  <heart_beat operation=\"notify\"/>";
    }
}
