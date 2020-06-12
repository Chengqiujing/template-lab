package com.chengqj.springbootsenior.net.encode;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;

/**
 * @Author chengqiujing
 * @Date 2020/6/9 14:01
 * @Desc
 */
public class AESUtil {
    private static final String KEY = "0123456789ABCDEF";

    /**
     * 加密
     */
    public static String encrypt(String text) throws Exception{
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        byte[] keyiv = KEY.getBytes();
        SecretKeySpec secretKey = new SecretKeySpec(keyiv,"AES");
        IvParameterSpec ivParam = new IvParameterSpec(keyiv);
        byte[] rawData = text.getBytes();
        int iBlockSize = cipher.getBlockSize();
        int iDataLength = rawData.length;
        if (iDataLength%iBlockSize != 0) {
            iDataLength = iDataLength+(iBlockSize-(iDataLength%iBlockSize));
        }
        byte[] toAesData = new byte[iDataLength];
        System.arraycopy(rawData,0,toAesData,0,rawData.length);
        cipher.init(Cipher.ENCRYPT_MODE,secretKey,ivParam);// 使用加密模式初始化 密钥
        byte[] encrypt = cipher.doFinal(toAesData,0, toAesData.length);// 按照单部分操作加密或者解密数据，或者结束一个多部份操作
        return byte2hex(encrypt);
    }
    /**
     * 加密
     */
    public static byte[] encryptToByteArray(String text) throws Exception{
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        byte[] keyiv = KEY.getBytes();
        SecretKeySpec secretKey = new SecretKeySpec(keyiv,"AES");
        IvParameterSpec ivParam = new IvParameterSpec(keyiv);
        byte[] rawData = text.getBytes();
        int iBlockSize = cipher.getBlockSize();
        int iDataLength = rawData.length;
        if (iDataLength%iBlockSize != 0) {
            iDataLength = iDataLength+(iBlockSize-(iDataLength%iBlockSize));
        }
        byte[] toAesData = new byte[iDataLength];
        System.arraycopy(rawData,0,toAesData,0,rawData.length);
        cipher.init(Cipher.ENCRYPT_MODE,secretKey,ivParam);// 使用加密模式初始化 密钥
        byte[] encrypt = cipher.doFinal(toAesData,0, toAesData.length);// 按照单部分操作加密或者解密数据，或者结束一个多部份操作
        return encrypt;
    }

    /**
     * 解密
     */
    public static String decrypt(String encryptStr) throws Exception{
        byte[] encrypt = hexStr2Byte(encryptStr);
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        byte[] keyiv = KEY.getBytes();
        SecretKeySpec secretKey = new SecretKeySpec(keyiv,"AES");
        IvParameterSpec ivParam = new IvParameterSpec(keyiv);
        cipher.init(Cipher.DECRYPT_MODE,secretKey,ivParam);// 使用解密模式初始化 密钥
        byte[] decrypt = cipher.doFinal(encrypt);
        return  new String(decrypt).trim();
    }
    /**
     * 解密从byte[]
     */
    public static String decryptFromByteArray(byte[] encrypt) throws Exception{
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        byte[] keyiv = KEY.getBytes();
        SecretKeySpec secretKey = new SecretKeySpec(keyiv,"AES");
        IvParameterSpec ivParam = new IvParameterSpec(keyiv);
        cipher.init(Cipher.DECRYPT_MODE,secretKey,ivParam);// 使用解密模式初始化 密钥
        byte[] decrypt = cipher.doFinal(encrypt);
        return  new String(decrypt).trim();
    }

    static String byte2hex(byte[] b){
        StringBuilder hs = new StringBuilder();
        String stmp;
        for(int n = 0;b!=null && n<b.length;n++){
            stmp = Integer.toHexString(b[n]& 0XFF);
            if (stmp.length() == 1) {
                hs.append('0');
            }
            hs.append(stmp);
        }
        return hs.toString().toUpperCase();
    }

    static byte[] hexStr2Byte(String hex){
        ByteBuffer bf = ByteBuffer.allocate(hex.length() / 2);
        for (int i = 0; i < hex.length(); i++) {
            String hexStr = hex.charAt(i) + "";
            i++;
            hexStr += hex.charAt(i);
            byte b = (byte)Integer.parseInt(hexStr, 16);
            bf.put(b);
        }
        return bf.array();
    }
}
