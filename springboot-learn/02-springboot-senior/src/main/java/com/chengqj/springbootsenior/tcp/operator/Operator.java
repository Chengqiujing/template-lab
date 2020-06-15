package com.chengqj.springbootsenior.tcp.operator;

import com.chengqj.springbootsenior.tcp.response.Response;

import java.io.IOException;

public interface Operator {

    public void send(String report) throws IOException;

    public Response receive() throws IOException;

    public boolean close();
}
