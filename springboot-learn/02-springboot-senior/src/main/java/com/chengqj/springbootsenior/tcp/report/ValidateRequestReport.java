package com.chengqj.springbootsenior.tcp.report;

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
    String generateBuildingNo() {
        return buildingNo;
    }

    @Override
    String generateCollectorNo() {
        return collectorNo;
    }

    @Override
    String generateType() {
        return ReportTypt.ID_VALIDATE_REQUEST;
    }

    @Override
    String generateBzReport() {
        return "<id_validate operation=\"request\"/>";
    }
}
