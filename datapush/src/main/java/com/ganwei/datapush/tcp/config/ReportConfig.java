package com.ganwei.datapush.tcp.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author chengqiujing
 * @Date 2020/6/12 14:58
 * @Desc
 */
@Component
@Data
public class ReportConfig {

    @Value("${weijing.report.buildingNo}")
    private String buildingNo;

    @Value("${weijing.report.collectorNo}")
    private String collectorNo;
}
