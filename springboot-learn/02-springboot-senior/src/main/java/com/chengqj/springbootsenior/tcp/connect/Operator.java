package com.chengqj.springbootsenior.tcp.connect;

import com.chengqj.springbootsenior.tcp.response.Response;

import java.io.IOException;

public interface Operator {

    public void send(String report);

    public Response receive() throws IOException;
}
