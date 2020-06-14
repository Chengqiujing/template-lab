package com.chengqj.springbootsenior.tcp.util;


/**
 * @Author chengqiujing
 * @Date 2020/6/9 16:15
 * @Desc
 */
public class ReportUtil {

    /**
     * 字节数组转十六进制
     * @param data
     * @return
     */
    public static String byteToHex(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (byte b : data) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    /**
     * 字节数组转int
     * @param bytes
     * @return
     */
    public static int byteArrayToInt(byte[] bytes) {
        reverseArray(bytes);
        int value = 0;
        // 由高位到低位
        for (int i = 0; i < 4; i++) {
            int shift = (4 - 1 - i) * 8;
            value += (bytes[i] & 0x000000FF) << shift;// 往高位游
        }
        return value;
    }

    public static void reverseArray(byte[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            byte temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }

}
