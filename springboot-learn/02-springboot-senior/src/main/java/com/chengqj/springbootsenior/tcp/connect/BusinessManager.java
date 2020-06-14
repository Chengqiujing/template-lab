package com.chengqj.springbootsenior.tcp.connect;

import com.chengqj.springbootsenior.tcp.config.ConnectConfig;
import com.chengqj.springbootsenior.tcp.config.ReportConfig;

/**
 * @Author chengqj
 * @Date 2020/6/11 0011 23:31
 * @Desc
 */
public class BusinessManager {
    public static void main(String[] args) {
        ConnectConfig connectConfig = new ConnectConfig();
        connectConfig.setIp("218.17.122.52");
        connectConfig.setPort(32886);

        ReportConfig reportConfig = new ReportConfig();
        reportConfig.setBuildingNo("GW123");
        reportConfig.setCollectorNo("GW001");

        DataOperator dataOperator = DataOperatorBuilder.getDataOperator(connectConfig, reportConfig);

        while(true){
            // 定时发送

            // 断网/错误重试

            //

        }

//        try {
//            TimeUnit.MINUTES.sleep(5);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }


    /**
     *
     * 缓存发
     *
     * 缓存收
     *
     *
     */
}
