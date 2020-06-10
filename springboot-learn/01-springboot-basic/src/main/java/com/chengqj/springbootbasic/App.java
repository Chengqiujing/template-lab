package com.chengqj.springbootbasic;


import com.chengqj.springbootbasic.filter.SecondFilter;
import com.chengqj.springbootbasic.listener.SecondListener;
import com.chengqj.springbootbasic.servlet.SecondServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }

    @Bean
    public ServletRegistrationBean getServletRegistrationBean(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new SecondServlet());
        bean.addUrlMappings("/Second");
        return bean;
    }

    @Bean
    public FilterRegistrationBean getFilterRegistrationBean(){
        FilterRegistrationBean bean  = new FilterRegistrationBean(new SecondFilter());
        bean.addUrlPatterns("*.do","/Second");
        return bean;
    }

    @Bean
    public ServletListenerRegistrationBean<SecondListener> getServletListenerRegistrationBean(){
        ServletListenerRegistrationBean<SecondListener> listener = new ServletListenerRegistrationBean<SecondListener>(new SecondListener());
        return listener;
    }
}
