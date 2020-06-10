package com.chengqj.springbootbasic.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//通过注解整合Listener
@WebListener
public class FirstListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Listener... init ...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Listener ... destroy ...");
    }
}
