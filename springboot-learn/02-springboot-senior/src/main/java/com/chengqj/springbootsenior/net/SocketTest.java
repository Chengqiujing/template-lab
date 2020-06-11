package com.chengqj.springbootsenior.net;

import com.chengqj.springbootsenior.net.encode.AESUtil;
import com.chengqj.springbootsenior.net.util.ReportUtil;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

/**
 * @Author chengqiujing
 * @Date 2020/6/9 11:03
 * @Desc
 */
public class SocketTest {
    public static void main(String[] args) throws Exception {
        String host = "218.17.122.52";
        int port = 32886;

        Socket socket = new Socket(host,port);
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        String validateRequest = ReportUtil.getValidateRequest("123", "123");
        System.out.println("有效数据报文:" + validateRequest);
        byte[] packg = ReportUtil.packg(validateRequest,null);

        byte[] bytes = new byte[4];

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    System.out.println("socket读取("+System.currentTimeMillis()+")");
                    try {
                        inputStream.read(bytes);
                        System.out.println(ReportUtil.byteToHex(bytes));
                        if(isStart(bytes)){
                            inputStream.read(bytes);
                            int dataLength = byteArrayToInt(bytes);
                            System.out.println("有效内容长度："+dataLength);
                            byte[] data = new byte[dataLength];
                            inputStream.read(data);
                            byte[] bytes2 = Arrays.copyOfRange(data, 4, data.length);
                            String s = AESUtil.decryptFromByteArray(bytes2);
                            System.out.println(s);
                        }

                        Thread.currentThread().sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        },"123");
        thread.start();

        System.out.println("请求验证");
        outputStream.write(packg);
        outputStream.flush();
        Thread.currentThread().sleep(1000*3);

        thread.join();
        //outputStream.close();
        //inputStream.close();
        //socket.close();
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
