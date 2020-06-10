package com.chengqj.springbootsenior.quartz;

import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

@Configuration
public class QuartzConfig {

    //创建Job对象
    @Bean
    public JobDetailFactoryBean jobDetailFactoryBean(){
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(QuartzDemo.class);//关联job类
        return factoryBean;
    }

    //创建trigger对象
    /*@Bean
    public SimpleTriggerFactoryBean simpleTriggerFactoryBean(JobDetailFactoryBean job){
            SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
            factoryBean.setJobDetail(job.getObject());//关联对象
            factoryBean.setRepeatInterval(2000);//执行毫秒数
            factoryBean.setRepeatCount(5);//重复次数
            return factoryBean;
    }*/

    /**
     * Cron Trigger
     * @return
     */
    @Bean
    public CronTriggerFactoryBean simpleTriggerFactoryBean(JobDetailFactoryBean job){
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(job.getObject());
        cronTriggerFactoryBean.setCronExpression("0/2 * * * * ?");
        return cronTriggerFactoryBean;
    }
    //创建Scheduler
    /*@Bean
    public SchedulerFactoryBean schedulerFactoryBean(SimpleTriggerFactoryBean simpleTriggerFactoryBean){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        //关联trigger
        schedulerFactoryBean.setTriggers(simpleTriggerFactoryBean.getObject());
        return schedulerFactoryBean;
    }*/


    /**
     * 2.这是就可以在这里将自己实现的jobFactory注入，具体实现可以看MyAdaptableJobFactory；
     *      可以如下这么写
     *      @Autowired
     *     MyAdaptableJobFactory myAdaptableJobFactory;
     *     但并不公用，所以不如放到参数处
     */

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(CronTriggerFactoryBean cronTriggerFactoryBean, MyAdaptableJobFactory myAdaptableJobFactory){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        //关联trigger
        schedulerFactoryBean.setTriggers(cronTriggerFactoryBean .getObject());
        schedulerFactoryBean.setJobFactory(myAdaptableJobFactory);
        return schedulerFactoryBean;
    }
}
