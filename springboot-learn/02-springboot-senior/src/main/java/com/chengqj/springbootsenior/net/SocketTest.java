package com.chengqj.springbootsenior.net;

import com.chengqj.springbootsenior.net.util.ReportUtil;

import java.io.*;
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
                        System.out.println(Arrays.toString(bytes));
                        Thread.currentThread().sleep(500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        },"123");
        thread.start();
        while (true){
            System.out.println("请求验证");
            outputStream.write(packg);
            outputStream.flush();
            Thread.currentThread().sleep(1000*3);
        }
        //outputStream.close();
        //inputStream.close();
        //socket.close();
    }
}
