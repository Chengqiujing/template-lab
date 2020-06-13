package com.chengqj.springbootsenior.tcp.connect;

import com.chengqj.springbootsenior.tcp.config.ReportConfig;
import com.chengqj.springbootsenior.tcp.encrypt.Encryptor;
import com.chengqj.springbootsenior.tcp.report.Report;
import com.chengqj.springbootsenior.tcp.report.ReportFactory;
import com.chengqj.springbootsenior.tcp.report.Response;
import com.chengqj.springbootsenior.tcp.util.CRC16Util;
import com.chengqj.springbootsenior.tcp.util.LogUtil;
import com.chengqj.springbootsenior.tcp.util.ReportUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Author chengqiujing
 * @Date 2020/6/11 17:30
 * @Desc
 */
public class DataOperator implements Operator{

    private static volatile boolean isStop = false;

    private static volatile int period = 5; // 分钟

    private static final int RETRY = 3;

    private int count = 1;

    private Connector connector;

    private Encryptor encryptor;

    public DataOperator(Connector connector, Encryptor encryptor) {
        this.connector = connector;
        this.encryptor = encryptor;
    }

    /**
     * 发送报文
     * @param report
     */
    public void send(String report){
        try {
            synchronized (this) {
                LogUtil.LOGGER.info(">>>>>>>>>>发送报文<<<<<<<<<<<\n"+report);
                byte[] packg = packg(report, null);
                connector.send(packg);
            }
        } catch (Exception e) {

            LogUtil.LOGGER.error(">>>>>>>>>>报文发送失败,重试第"+count+"次<<<<<<<<<<<",e);
            if(count <= RETRY){
                count++;
                send(report);
            }
            count = 1;
        }
    }

    /**
     * 收取报文
     * @return
     * @throws IOException
     */
    public Response receive() throws IOException {
        LogUtil.LOGGER.info(">>>>>>>>>>等待接收报文<<<<<<<<<<<");
        byte[] bytes = new byte[4];
        byte[] crc = new byte[2];
        int count = 0; // 防止回写数据为无效数据，倒置CPU升高
        int receive;
        while(!isEnd(bytes)){
            receive = connector.receive(bytes);
            if(isStart(bytes)){
                connector.receive(bytes);
                int dataLength = ReportUtil.byteArrayToInt(bytes); // 获取有效数据长度
                connector.receive(bytes); // 跳过指令序号，业务中没有
                byte[] data = new byte[dataLength-4];
                connector.receive(data);
                String decrypt = encryptor.decrypt(data);
                Response response = new Response();
                response.setText(decrypt);
                connector.receive(crc);
                connector.receive(bytes);

                LogUtil.LOGGER.info(">>>>>>>>>>接收报文<<<<<<<<<<<\n"+response.getText());
                return response;
            }
            count++;
            if(count > 1000){ // 收到无效数据超过 4*1000字节后（空跑1000次），转入低频率接收
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(count > 1060){ // 以500ms一次，空跑30s后返回null
                    LogUtil.LOGGER.error("X》X》X》接收无效数据超过30s，接收停止，返回null《X《X《X");
                    return null;
                }
            }
        }
        return null;
    }

    /**
     * 销毁
     */
    public void destroyDataOperator(){
        LogUtil.LOGGER.info(">>>>开始销毁>>>>>>>>>");
        if (connector != null) {
            try {
                connector.close();
            } catch (IOException e) {
                LogUtil.LOGGER.error("销毁失败，请重启",e);
            }
        }
        isStop = true;
        LogUtil.LOGGER.info(">>>>销毁完成>>>>>>>>>");
    }

    // 心跳
    public void heartBeat( ReportConfig reportConfig) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    if(isStop){
                        LogUtil.LOGGER.info("心跳线程停止...咚~咚...咚~.........");
                        return;
                    }

                    Report heartBeatReport = ReportFactory.getHeartBeatReport(reportConfig.getBuildingNo(), reportConfig.getCollectorNo());
                    send(heartBeatReport.getReport());
                    try {
                        Response receive = receive();
                        LogUtil.LOGGER.info("心跳延时报文\n"+receive.getText());
                    } catch (IOException e) {
                        e.printStackTrace();
                        LogUtil.LOGGER.warn("心跳延时信息接收失败",e);
                    }

                    try {
                        TimeUnit.SECONDS.sleep(period*3);
                    } catch (InterruptedException e) {
                        LogUtil.LOGGER.error("心跳线程睡眠被打断",e);
                    }
                }
            }
        }, "Heart Beat");
        thread.start();
    }

    // 身份验证
    public boolean validate(ReportConfig reportConfig) {
        try {
            LogUtil.LOGGER.info("》》》身份验证开始《《《");
            // 采集器编号，建筑编号是否为空
            if (reportConfig.getBuildingNo() == null || reportConfig.getCollectorNo() == null) {
                LogUtil.LOGGER.error("------>报文配置项为空（" + ReportConfig.class.getName() + "），身份无法验证<------");
                return false;
            }
            // 身份验证：获取sequence
            Report idValidateRequestReport = ReportFactory.getIdValidateRequestReport(reportConfig.getBuildingNo(), reportConfig.getCollectorNo());
            send(idValidateRequestReport.getReport());
            Response receive = receive();
            String sequence = receive.getContentByPath("/root/id_validate/sequence");
            if (Objects.isNull(sequence)) {
                LogUtil.LOGGER.error("身份验证异常：返回sequence为空");
                return false;
            }
            LogUtil.LOGGER.info("身份验证：sequence=" + sequence);

            // MD5再次验证
            Report idValidateMD5Report = ReportFactory
                    .getIdValidateMD5Report(reportConfig.getBuildingNo(), reportConfig.getCollectorNo(), sequence);
            send(idValidateMD5Report.getReport());
            Response md5Receive = receive();
            String result = md5Receive.getContentByPath("result");
            if ("pass".equals(result)) {
                LogUtil.LOGGER.error("》》》身份验证通过《《《");
                return true;
            } else {
                LogUtil.LOGGER.error("》》》身份验证失败：未通过《《《");
                return false;
            }
        } catch (IOException e) {
            LogUtil.LOGGER.error("身份验证异常：流错误", e);
        }
        return false;
    }


    /**
     * 打包
     * 包头 4字节 0x68 0x68 0x16 0x16
     * 有效数据总长度 4字节
     * 有效数据 n字节=m字节+4字节 4字节指令序号 m字节
     * CRC校验 2字节 只对有效数据进行CRC校验
     * 包尾 4字节 0x55 0xAA 0x55 0xAA
     */
    private byte[] packg(String report,Integer sequence) throws Exception {
        System.out.println("AES加密后：" + ReportUtil.byteToHex(encryptor.encrypt(report)));
        byte[] bytes = encryptor.encrypt(report); // 加密后指令内容

        System.out.println("加密后数组长度："+bytes.length);
        int length = bytes.length + 4 + 4 + 4 + 4 + 2;
        int dataLenght = bytes.length + 4;
        byte[] packBytes = new byte[length];
        // 包头
        packBytes[0] = 0x68;
        packBytes[1] = 0x68;
        packBytes[2] = 0x16;
        packBytes[3] = 0x16;
        // 包尾
        packBytes[packBytes.length - 4] = 0x55;
        packBytes[packBytes.length - 3] = (byte) 0xAA;
        packBytes[packBytes.length - 2] = 0x55;
        packBytes[packBytes.length - 1] = (byte) 0xAA;
        // 有效数据总长度
        packBytes[7] = (byte) (dataLenght >>> 24);
        packBytes[6] = (byte) (dataLenght >>> 16);
        packBytes[5] = (byte) (dataLenght >>> 8);
        packBytes[4] = (byte) dataLenght;

        // 指令序号 四个空字节 8,9,10,11
        if(sequence != null){
            packBytes[11] = (byte) (sequence >>> 24);
            packBytes[10] = (byte) (sequence >>> 16);
            packBytes[9] = (byte) (sequence >>> 8);
            packBytes[8] = (byte) sequence.intValue();
        }
        // 有效数据
        System.arraycopy(bytes, 0, packBytes, 12, bytes.length);

        System.out.println("crc16进制字节数组:" + ReportUtil.byteToHex(Arrays.copyOfRange(packBytes,8,dataLenght+8)));
        // CRC校验
        int crc = CRC16Util.getCRC(Arrays.copyOfRange(packBytes,8,dataLenght+8));
        System.out.println("CRC校验：" + Integer.toHexString(crc));
        packBytes[packBytes.length - 5] = (byte) (crc >>> 8);
        packBytes[packBytes.length - 6] = (byte) crc;

        System.out.println("最终发送包："+ ReportUtil.byteToHex(packBytes));
        return packBytes;
    }


    public static boolean isStart(byte[] bytes){
        return bytes[0] == 0x68
                && bytes[1] == 0x68
                && bytes[2] == 0x16
                && bytes[3] == 0x16;
    }
    public static boolean isEnd(byte[] bytes){
        return bytes[0] == 0x55
                && bytes[1] == 0xAA
                && bytes[2] == 0x55
                && bytes[3] == 0xAA;
    }

    public Encryptor getEncryptor() {
        return encryptor;
    }

    public void setEncryptor(Encryptor encryptor) {
        this.encryptor = encryptor;
    }
}
