package com.chengqj.springbootsenior.tcp.report;

/**
 * @Author chengqiujing
 * @Date 2020/6/12 10:01
 * @Desc
 */
public abstract class AbstractReport implements Report{

    public String getReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n")
                .append("<root>\n")
                .append("    <common>\n")
                .append("      <building_id>")
                .append(generateBuildingNo())
                .append("</building_id>\n")
                .append("      <gateway_id>")
                .append(generateCollectorNo())
                .append("</gateway_id>\n")
                .append("      <type>")
                .append(generateType())
                .append("</type>\n")
                .append("    </common>\n")
                .append(generateBzReport())
                .append("</root>");
        return sb.toString();
    }

    protected abstract String generateBuildingNo();

    protected abstract String generateCollectorNo();

    protected abstract String generateType();

    protected abstract String generateBzReport();

}
