package com.chengqj.springbootsenior.net;

import com.chengqj.springbootsenior.net.encode.AESUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @Author chengqiujing
 * @Date 2020/6/10 13:41
 * @Desc
 */
public class ServerSocketTest {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(32886);
        System.out.println("-----qidong------");
        Socket socket = serverSocket.accept();
        System.out.println("-------in-----------");
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        byte[] bytes = new byte[4];
        while(true){
            inputStream.read(bytes);
            System.out.println("server读取");
            if(isStart(bytes)){
                inputStream.read(bytes);
                int dataLength = byteArrayToInt(bytes);
                System.out.println("lenght:"+dataLength);
                byte[] bytes1 = new byte[dataLength];
                inputStream.read(bytes1);
                byte[] bytes2 = Arrays.copyOfRange(bytes1, 4, bytes1.length);
                String content = new String(bytes2, StandardCharsets.UTF_8);
                System.out.println(content);
                String decrypt = AESUtil.decrypt(content);
                System.out.println(decrypt);

                outputStream.write("ok".getBytes());
            }
            Thread.currentThread().sleep(500);
        }

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
        int value = 0;
        // 由高位到低位
        for (int i = 0; i < 4; i++) {
            int shift = (4 - 1 - i) * 8;
            value += (bytes[i] & 0x000000FF) << shift;// 往高位游
        }
        return value;
    }
}
