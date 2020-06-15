package com.chengqj.springbootsenior.tcp.report.impl;

import com.chengqj.springbootsenior.tcp.report.AbstractReport;
import com.chengqj.springbootsenior.tcp.report.ReportTypt;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author chengqj
 * @Date 2020/6/16 0016 0:16
 * @Desc
 */
@Data
@AllArgsConstructor
public class AutoHistoryReport extends AbstractReport {

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
        return ReportTypt.DATA_AUTO_HISTORY;
    }

    @Override
    protected String generateBzReport() {
        return "  <Instruction operation=\"auto_history\" />";
    }
}
