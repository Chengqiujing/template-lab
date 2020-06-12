package com.chengqj.springbootsenior.tcp.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author chengqiujing
 * @Date 2020/6/12 14:09
 * @Desc 计量装置的具体采集功能
 */
@Data
public class Function {
    /**
     * 计量装置的具体采集功能编号
     */
    private String functionId;
    /**
     * 能耗数据分类/分项编号
     */
    private String functionCoding;
    /**
     * 数据是否错误，0 表示没有错误
     */
    private String functionError;
    /**
     * 采集时间 yyyyMMddHHmmss
     */
    private LocalDateTime functionSampleTime;
    /**
     * 数据值
     */
    private Double functionData;



}
