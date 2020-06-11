package com.chengqj.springbootsenior.tcp.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;

/**
 * @Author chengqiujing
 * @Date 2020/6/9 13:37
 * @Desc
 */
public class MD5Util {
    private static final String SEED = "0123456789ABCDEF";

    private static final String SALT = "12345";

    public static String generateSquence(){
        return RandomStringUtils.random(16,SEED);
    }

    public static String md5Encode(String input,String salt){
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            if(StringUtils.isEmpty(salt)){
                salt = SALT;
            }
            byte[] bytes = md5.digest((input+salt).getBytes("utf-8"));
            return toHex(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String toHex(byte[] bytes){
        final char[] HEX_DIGITS = SEED.toCharArray();
        StringBuilder strBuilder = new StringBuilder(bytes.length*2);
        for (int i = 0; i < bytes.length; i++) {
            strBuilder.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0F]);
            strBuilder.append(HEX_DIGITS[bytes[i] & 0x0F]);
        }
        return strBuilder.toString();
    }
}
