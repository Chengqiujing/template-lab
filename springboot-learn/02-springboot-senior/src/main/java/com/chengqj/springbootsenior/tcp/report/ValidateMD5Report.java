package com.chengqj.springbootsenior.tcp.report;

import com.chengqj.springbootsenior.tcp.util.MD5Util;
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
    String generateBuildingNo() {
        return buildingNo;
    }

    @Override
    String generateCollectorNo() {
        return collectorNo;
    }

    @Override
    String generateType() {
        return ReportTypt.ID_VALIDATE_MD5;
    }

    @Override
    String generateBzReport() {
        return "<id_validate operation=\"md5\"><md5>" + MD5Util.md5Encode(sequence, null) + "</md5></id_validate>"; // md5编码;
    }
}
