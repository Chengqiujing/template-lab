package com.chengqj.springbootsenior.exceptionhandler;

import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.Properties;

/**
 * 注释掉 因为采用了自定义异常处理
 */
//@Configuration
public class SimpleGlobalException {

    /**
     * 这种方式不能返回异常信息，不好
     * @return
     */
    //@Bean
    public SimpleMappingExceptionResolver getSimpleMappingExceptionResolver(){
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        /**
         * 参数一：异常类型，必须是异常类型的全名
         * 参数二：视图名称
         */
        mappings.put("java.lang.NullPointerException","error");
        mappings.put("java.lang.ArithmeticException","error2");
        resolver.setExceptionMappings(mappings);
        return resolver;
    }
}
