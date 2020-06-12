package com.chengqj.springbootsenior.net.util;

import com.chengqj.springbootsenior.net.encode.AESUtil;
import com.chengqj.springbootsenior.net.encode.CRC16Util;
import com.chengqj.springbootsenior.net.encode.MD5Util;

import java.util.Arrays;

/**
 * @Author chengqiujing
 * @Date 2020/6/9 16:15
 * @Desc
 */
public class ReportUtil {


    // 鉴权请求报文 1

    private static String getReport(String buildingNo, String collectorNo, String type, String bzReport) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>")
                .append("<root>")
                .append("<common>")
                .append("<building_id>")
                .append(buildingNo)
                .append("</building_id>")
                .append("<gateway_id>")
                .append(collectorNo)
                .append("</gateway_id>")
                .append("<type>")
                .append(type)
                .append("</type>")
                .append("</common>")
                .append(bzReport)
                .append("</root>");
        return sb.toString();
    }


    // 鉴权request
    public static String getValidateRequest(String buildingNo, String collectorNo) {
//        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
//                "<root>\n" +
//                "    <common>\n" +
//                "      <building_id>123</building_id>\n" +
//                "      <gateway_id>123</gateway_id>\n" +
//                "      <type>request</type>\n" +
//                "    </common>\n" +
//                "    <id_validate operation=\"request\"/>\n" +
//                "</root>";
        String bzReport = "<id_validate operation=\"request\"/>";
        String type = "request";
        return getReport(buildingNo, collectorNo, type, bzReport);
    }

    // 鉴权 md5
    public static String getValidateMD5(String buildingNo, String collectorNo, String sequence) {
        String bzReport = "<id_validate operation=\"md5\"><md5>" + MD5Util.md5Encode(sequence, null) + "</md5></id_validate>"; // md5编码
        String type = "md5";
        return getReport(buildingNo, collectorNo, type, bzReport);
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

        System.out.println("crc16进制字节数组:" + byteToHex(Arrays.copyOfRange(packBytes,8,dataLenght+8)));
        // CRC校验
        int crc = CRC16Util.getCRC(Arrays.copyOfRange(packBytes,8,dataLenght+8));
        //int crc = 0xB563;
        System.out.println("CRC校验：" + Integer.toHexString(crc));
        packBytes[packBytes.length - 5] = (byte) (crc >>> 8);
        packBytes[packBytes.length - 6] = (byte) crc;

        System.out.println("最终发送包："+byteToHex(packBytes));
        return packBytes;
    }


    public static String byteToHex(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (byte b : data) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static void reverseArray(byte[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            byte temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }
}
