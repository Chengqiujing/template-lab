package com.chengqj.springbootsenior.tcp.encrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @Author chengqiujing
 * @Date 2020/6/11 14:30
 * @Desc
 */
public class AESEncryptor implements Encryptor {
    private static final String KEY = "0123456789ABCDEF";

    @Override
    public byte[] encrypt(String text) {
        try {
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
        } catch (Exception e) {
            throw new RuntimeException("加密失败",e);
        }
    }

    @Override
    public String decrypt(byte[] encrypt) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            byte[] keyiv = KEY.getBytes();
            SecretKeySpec secretKey = new SecretKeySpec(keyiv,"AES");
            IvParameterSpec ivParam = new IvParameterSpec(keyiv);
            cipher.init(Cipher.DECRYPT_MODE,secretKey,ivParam);// 使用解密模式初始化 密钥
            byte[] decrypt = cipher.doFinal(encrypt);
            return  new String(decrypt).trim();
        } catch (Exception e) {
            throw new RuntimeException("解密失败",e);
        }
    }
}
