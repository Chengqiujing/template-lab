package com.ganwei.datapush.tcp.response.impl;

import com.ganwei.datapush.tcp.config.ReportConfig;
import com.ganwei.datapush.tcp.operator.Operator;
import com.ganwei.datapush.tcp.report.Report;
import com.ganwei.datapush.tcp.report.ReportFactory;
import com.ganwei.datapush.tcp.response.Response;
import com.ganwei.datapush.tcp.response.ResponseResolver;
import com.ganwei.datapush.tcp.service.BusinessManager;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @Author chengqiujing
 * @Date 2020/6/17 9:37
 * @Desc
 */
@AllArgsConstructor
public class ConfigResponseResolver implements ResponseResolver {
    private final Logger logger = LoggerFactory.getLogger(ConfigResponseResolver.class);

    private ReportConfig reportConfig;
    @Override
    public void dealWith(Response response, Operator operator) {
        String contentByPath = response.getContentByPath("/root/config/period");
        if(contentByPath != null){
            Integer integer = Integer.valueOf(contentByPath);
            logger.info("数据发送频率改为："+integer+"分钟/次");
            BusinessManager.dataPeriod = integer;

            Report configAckReport = ReportFactory.getConfigAckReport(reportConfig.getBuildingNo(), reportConfig.getCollectorNo());

            try {
                operator.send(configAckReport.getReport());
            } catch (IOException e) {
                logger.info("配置频率反馈报文，发送失败");
            }

        }
    }
}
