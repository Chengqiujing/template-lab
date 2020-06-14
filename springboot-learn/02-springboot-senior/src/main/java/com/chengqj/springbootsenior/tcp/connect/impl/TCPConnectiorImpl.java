package com.chengqj.springbootsenior.tcp.connect.impl;

import com.chengqj.springbootsenior.tcp.config.ConnectConfig;
import com.chengqj.springbootsenior.tcp.connect.Connector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @Author chengqiujing
 * @Date 2020/6/11 14:48
 * @Desc
 */
public class TCPConnectiorImpl implements Connector {

    private Socket socket;

    private InputStream inputStream;

    private OutputStream outputStream;

    @Override
    public void init(ConnectConfig config) throws IOException {
        socket = new Socket(config.getIp(),config.getPort());
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
    }

    @Override
    public void send(byte[] bytes) throws IOException {
        if(outputStream != null){
            outputStream.write(bytes);
            outputStream.flush();
        }
    }

    @Override
    public int receive(byte[] bytes) throws IOException {
        if(inputStream != null){
            return inputStream.read(bytes);
        }
        return  -1;
    }

    @Override
    public void close() throws IOException {
        if (inputStream != null) {
            inputStream.close();
        }
        if (outputStream != null) {
            outputStream.close();
        }
        if (socket != null) {
            socket.close();
        }
    }
}
