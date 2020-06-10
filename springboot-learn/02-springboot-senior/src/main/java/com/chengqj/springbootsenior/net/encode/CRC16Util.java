package com.chengqj.springbootsenior.net.encode;

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
                boolean bit = ((b >> (7-i)&1)==1);
                boolean c15 = ((crc >> 15 &1) == 1);
                crc <<=1;
                if(c15 ^ bit)crc^= polynomial;
            }
        }
        crc &= 0xffff;
        return crc;
    }
}
