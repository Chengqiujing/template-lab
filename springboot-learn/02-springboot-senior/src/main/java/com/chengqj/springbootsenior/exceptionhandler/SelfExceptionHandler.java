package com.chengqj.springbootsenior.exceptionhandler;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class SelfExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        if(e instanceof NullPointerException){
            modelAndView.setViewName("error");
        }else if(e instanceof ArithmeticException){
            modelAndView.setViewName("error2");
        }
        modelAndView.addObject("error",e);
        return modelAndView;
    }
}
