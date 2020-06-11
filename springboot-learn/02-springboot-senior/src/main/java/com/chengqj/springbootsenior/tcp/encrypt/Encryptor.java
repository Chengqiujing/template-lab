package com.chengqj.springbootsenior.tcp.encrypt;

/**
 * @Author chengqiujing
 * @Date 2020/6/11 14:23
 * @Desc
 */
public interface Encryptor {

    /**
     * 加密
     */
    byte[] encrypt(String text);

    /**
     * 解密
     */
    String decrypt(byte[] bytes);
}
