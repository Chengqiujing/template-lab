package com.ganwei.datapush.tcp.operator;


import com.ganwei.datapush.tcp.response.Response;

import java.io.IOException;

public interface Operator {

    public void send(String report) throws IOException;

    public Response receive() throws IOException;

    public boolean close();
}
