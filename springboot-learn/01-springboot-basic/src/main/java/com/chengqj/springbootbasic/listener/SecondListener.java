package com.chengqj.springbootbasic.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//通过方法整合Listener
public class SecondListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("SecondListener ... init ...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("SecondListener... destroy...");
    }
}
