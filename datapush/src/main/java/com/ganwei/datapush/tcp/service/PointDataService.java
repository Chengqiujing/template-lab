package com.ganwei.datapush.tcp.service;

import com.ganwei.datapush.tcp.entity.Meter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author chengqiujing
 * @Date 2020/6/15 17:12
 * @Desc
 */
@Component
public interface PointDataService {

    public List<Meter> getData();
}
