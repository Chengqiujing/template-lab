package com.chengqj.springbootsenior.tcp.entity;

import lombok.Data;

import java.util.List;

/**
 * @Author chengqiujing
 * @Date 2020/6/12 14:09
 * @Desc
 */
@Data
public class Meter {
    /**
     * 仪表ID
     */
    private String meterId;
    /**
     * 仪表名
     */
    private String meterName;
    /**
     * 功能点
     */
    private List<Function> functions;
}
