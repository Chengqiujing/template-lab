package com.chengqj.springbootsenior.tcp.util;

/**
 * @Author chengqiujing
 * @Date 2020/6/9 14:36
 * @Desc
 */
public class CRC16Util {
    /**
     * 计算CRC16校验码：CRC-CCITT(XModem)
     */
    public static int getCRC(byte[] bytes){
        int crc = 0x00; // initial value
        int polynomial = 0x1021;
        for(int index =0;index<bytes.length;index++){
            byte b = bytes[index];
            for (int i = 0; i < 8; i++) {
                boolean bit = ((b >> (7-i)&1)==1);  // byte的最高位(依次递减)是不是1
                boolean c15 = ((crc >> 15 &1) == 1); // crc的最高位(依次递减)是不是1
                crc <<=1; // byte每往下数一位,crc左移1位
                if(c15 ^ bit)crc^= polynomial; // 当crc的最高位和byte的递减位不同时, crc会除以多项式
            }
        }
        crc &= 0xffff;
        return crc;
    }


//    public static void main(String[] args) {
//        String tmp = "testadsfasdfasdfadf1qweqwe2314sdfgsd\"\"";
//        byte[] bytes = tmp.getBytes();
//        int crc = getCRC(bytes);
//        byte[] bytes1 = Arrays.copyOf(bytes, bytes.length + 2);
//        //bytes1[0] = (byte)0x80;
//        bytes1[bytes1.length-1] = (byte)crc; // 低位
//        bytes1[bytes1.length-2] = (byte)(crc >> 8); // 高位
//
//        System.out.println(getCRC(bytes1));
//    }

}
