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
     * 数据采集时间
     */
    private String time;


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
          .append("</time>\n")
          .append("    <meter id=\"32\" name=\"00000000000001\" conn=\"conn\">\n")
          .append("      <function id=\"0001\" coding=\"01B00\" error=\"0\" sample_time=\"20181222143418\">9600.00</function>\n")
          .append("    </meter>\n")
          .append("    <meter id=\"33\" name=\"00000000000001\" conn=\"conn\">\n")
          .append("      <function id=\"0001\" coding=\"01B00\" error=\"0\" sample_time=\"20181222143418\">20.00</function>\n")
          .append("    </meter>\n")
          .append("    <meter id=\"34\" name=\"00000000000001\" conn=\"conn\">\n")
          .append("      <function id=\"0001\" coding=\"01B00\" error=\"0\" sample_time=\"20181222143418\">1.00</function>\n")
          .append("    </meter>\n")
          .append("  </data>");

        return sb.toString();
    }
}
