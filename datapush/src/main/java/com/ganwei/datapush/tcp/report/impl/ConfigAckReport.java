package com.ganwei.datapush.tcp.report.impl;

import com.ganwei.datapush.tcp.report.AbstractReport;
import com.ganwei.datapush.tcp.report.ReportTypt;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author chengqiujing
 * @Date 2020/6/17 14:12
 * @Desc
 */
@Data
@AllArgsConstructor
public class ConfigAckReport extends AbstractReport {
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
        return ReportTypt.CONFIG_ACK;
    }

    @Override
    protected String generateBzReport() {
        return "<config operation=\"period_ack\" ></config>";
    }
}
