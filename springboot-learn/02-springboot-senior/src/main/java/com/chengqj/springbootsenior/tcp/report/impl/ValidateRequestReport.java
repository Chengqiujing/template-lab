package com.chengqj.springbootsenior.tcp.report.impl;

import com.chengqj.springbootsenior.tcp.report.AbstractReport;
import com.chengqj.springbootsenior.tcp.report.ReportTypt;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author chengqiujing
 * @Date 2020/6/12 10:10
 * @Desc
 */
@Data
@AllArgsConstructor
public class ValidateRequestReport extends AbstractReport {

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
        return ReportTypt.ID_VALIDATE_REQUEST;
    }

    @Override
    protected String generateBzReport() {
        return "<id_validate operation=\"request\"/>";
    }
}
