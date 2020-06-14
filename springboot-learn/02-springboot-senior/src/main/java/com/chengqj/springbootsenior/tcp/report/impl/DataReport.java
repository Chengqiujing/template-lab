package com.chengqj.springbootsenior.tcp.report.impl;

import com.chengqj.springbootsenior.tcp.entity.Function;
import com.chengqj.springbootsenior.tcp.entity.Meter;
import com.chengqj.springbootsenior.tcp.report.AbstractReport;
import com.chengqj.springbootsenior.tcp.report.ReportTypt;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author chengqiujing
 * @Date 2020/6/12 11:38
 * @Desc
 */
@Data
@AllArgsConstructor
public class DataReport extends AbstractReport {

    private String buildingNo;

    private String collectorNo;

    /**
     * 采集器向数据中心发送数据的序号
     */
    private String dataSequence;

    /**
     * 采集器是否解析
     */
    private boolean parse;

    /**
     * 数据采集时间 yyyyMMddHHmmss
     */
    private LocalDateTime time;

    /**
     * 仪表数据
     */
    List<Meter> meters;


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
        StringBuilder sb = new StringBuilder();
        sb.append("  <data operation=\"report\">\n")
          .append("    <sequence>")
          .append(dataSequence)
          .append("</sequence>\n")
          .append("    <parse>")
          .append(parse? "yes":"no")
          .append("</parse>\n")
          .append("    <time>")
          .append(time)
          .append("</time>\n");

        for (Meter meter : meters) {
            sb.append("    <meter id=\"").append(meter.getMeterId()).append("\" name=\"").append(meter.getMeterName()).append("\" conn=\"conn\">\n");
            List<Function> functions = meter.getFunctions();
            for (Function function : functions) {
                sb.append("      <function id=\"").append(function.getFunctionId()).append("\" coding=\"").append(function.getFunctionCoding())
                        .append("\" error=\"").append(function.getFunctionError()).append("\" sample_time=\"").append(function.getFunctionSampleTime())
                        .append("\">").append(function.getFunctionData()).append("</function>\n");
            }
            sb.append("    </meter>\n");
        }
        sb.append("  </data>");
        return sb.toString();
    }
}
