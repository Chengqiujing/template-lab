package com.chengqj.springbootsenior.quartz;

import com.chengqj.springbootsenior.service.UserService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class QuartzDemo implements Job {
    /**
     *
     * 1.当调用springIOC时，发现不可以使用注入
     *      原因是QuartzDemo对象不存在容器中（spring的IOC要求注入和被注入的对象都要在容器中管理），是因为这个对象的产生是由Config中的JobDetailFactoryBean产生的
     *      这样要求我们在config中做调整，实现自己的jobFactory
     */
    @Autowired
    private UserService userService;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("---->  " + new Date());
        userService.addUser();
    }
}
