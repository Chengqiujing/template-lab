package com.chengqj.springbootsenior.tcp.connect;

import com.chengqj.springbootsenior.tcp.Report;

import java.io.IOException;

public interface Operator {

    public void send(String report);

    public Report receive() throws IOException;
}
