package com.chengqj.springbootsenior.quartz;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component("myAdaptableJobFactory")
public class MyAdaptableJobFactory extends AdaptableJobFactory {
    //该对象会将对象添加到springIOC容器中，并注入
   @Autowired
    private AutowireCapableBeanFactory autoFactory;
    /**
     * 将实例化的任务对象手动的添加到springIOC容器中并且完成对象的注入
     */
    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object jobInstance = super.createJobInstance(bundle);
        //添加到容器 ，并注入
        autoFactory.autowireBean(jobInstance);
        return jobInstance;
    }
}
