package com.ganwei.datapush.tcp.report;

/**
 * @Author chengqiujing
 * @Date 2020/6/12 12:55
 * @Desc 报文type类型
 */
public class ReportTypt {
    private ReportTypt(){}

    /**
     * 身份验证
     */
    public final static String ID_VALIDATE_REQUEST = "request";
    public final static String ID_VALIDATE_SEQUENCE = "sequence";
    public final static String ID_VALIDATE_MD5 = "md5";
    public final static String ID_VALIDATE_RESULT = "result";

    /**
     * 心跳
     */
    public final static String HEART_BEAT_NOTIFY = "notify";
    public final static String HEART_BEAT_TIME = "time";

    /**
     * 数据上传
     */
    public final static String DATA_QUERY = "query";
    public final static String DATA_REPLY = "reply";
    public final static String DATA_REPORT = "report";
    public final static String DATA_CONTINUOUS = "continuous";
    public final static String DATA_CONTINUOUS_ACK = "continuous_ack";

    /**
     * 主动历史数据上传
     */
    public final static String DATA_AUTO_HISTORY = "auto_history";
    public final static String DATA_AUTO_HISTORY_ACK = "auto_history_ack";

    /**
     * 配置
     */
    public final static String CONFIG_PERIOD = "period";
    public final static String CONFIG_ACK = "period_ack";
}
