package com.chengqj.springbootsenior.tcp.connect;

import com.chengqj.springbootsenior.exceptionhandler.GlobalExceptionHandler;
import com.chengqj.springbootsenior.net.encode.AESUtil;
import com.chengqj.springbootsenior.net.util.ReportUtil;
import com.chengqj.springbootsenior.tcp.Report;
import com.chengqj.springbootsenior.tcp.encrypt.Encryptor;
import com.chengqj.springbootsenior.tcp.util.CRC16Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;

/**
 * @Author chengqiujing
 * @Date 2020/6/11 17:30
 * @Desc
 */
public class DataOperator {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private Connector connector;

    private Encryptor encryptor;


    public void send(String report){
        byte[] packg = new byte[0];
        try {
            packg = packg(report, null);
            connector.send(packg);
        } catch (Exception e) {
            logger.error("发送失败",e);
        }
    }

    public Report receive() throws IOException {
        byte[] bytes = new byte[4];
        while(true){
            int receive = connector.receive(bytes);
            if(isStart(bytes)){
                connector.receive(bytes);
                int dataLength = byteArrayToInt(bytes); // 获取有效数据长度
                connector.receive(bytes); // 跳过指令序号，业务中没有
                byte[] data = new byte[dataLength-4];
                connector.receive(data);
                String decrypt = encryptor.decrypt(data);
                Report report = new Report();
                report.setText(decrypt);
                return report;
            }
        }
    }

    /**
     * 包头 4字节 0x68 0x68 0x16 0x16
     * 有效数据总长度 4字节
     * 有效数据 n字节=m字节+4字节 4字节指令序号 m字节
     * CRC校验 2字节 只对有效数据进行CRC校验
     * 包尾 4字节 0x55 0xAA 0x55 0xAA
     */
    public static byte[] packg(String report,Integer sequence) throws Exception {
        System.out.println("AES加密后：" + AESUtil.encrypt(report));
        byte[] bytes = AESUtil.encryptToByteArray(report); // 加密后指令内容
        //reverseArray(bytes);
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
        //int crc = 0xB563;
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

    public static int byteArrayToInt(byte[] bytes) {
        ReportUtil.reverseArray(bytes);
        int value = 0;
        // 由高位到低位
        for (int i = 0; i < 4; i++) {
            int shift = (4 - 1 - i) * 8;
            value += (bytes[i] & 0x000000FF) << shift;// 往高位游
        }
        return value;
    }

}
