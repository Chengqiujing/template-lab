package com.ganwei.datapush.tcp.report.impl;


import com.ganwei.datapush.tcp.report.AbstractReport;
import com.ganwei.datapush.tcp.report.ReportTypt;
import com.ganwei.datapush.tcp.util.MD5Util;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author chengqiujing
 * @Date 2020/6/12 11:38
 * @Desc
 */
@Data
@AllArgsConstructor
public class ValidateMD5Report extends AbstractReport {

    private String buildingNo;

    private String collectorNo;

    private String sequence;

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
        return ReportTypt.ID_VALIDATE_MD5;
    }

    @Override
    protected String generateBzReport() {
        return "<id_validate operation=\"md5\"><md5>" + MD5Util.md5Encode(sequence, null).toLowerCase() + "</md5></id_validate>"; // md5编码;
    }
}
