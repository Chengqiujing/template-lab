package com.chengqj.springbootsenior.tcp.report;

/**
 * @Author chengqiujing
 * @Date 2020/6/12 12:55
 * @Desc
 */
public class ReportTypt {
    private ReportTypt(){}

    public final static String ID_VALIDATE_REQUEST = "request";
    public final static String ID_VALIDATE_SEQUENCE = "sequence";
    public final static String ID_VALIDATE_MD5 = "md5";
    public final static String ID_VALIDATE_RESULT = "result";

    public final static String HEART_BEAT_NOTIFY = "notify";
    public final static String HEART_BEAT_TIME = "time";

    public final static String DATA_QUERY = "query";
    public final static String DATA_REPLY = "reply";
    public final static String DATA_CONTINUOUS = "continuous";
    public final static String DATA_CONTINUOUS_ACK = "continuous_ack";


    public final static String DATA_AUTO_HISTORY = "auto_history";
    public final static String DATA_AUTO_HISTORY_ACK = "auto_history_ack";



}
