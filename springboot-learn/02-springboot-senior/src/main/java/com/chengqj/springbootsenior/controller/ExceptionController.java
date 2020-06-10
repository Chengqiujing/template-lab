package com.chengqj.springbootsenior.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionController {

    @RequestMapping("/show")
    public String showInfo(){
        String str = null;
        str.length();
        return "index";
    }

    @RequestMapping("/show2")
    public String showInfo2(){
        int a = 10/0;
        return "index";
    }

/*
采用全局异常处理，所以这里注释掉
 */
//    @ExceptionHandler(NullPointerException.class)
//    public ModelAndView nullPointerExeption(Exception e){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("error");
//        modelAndView.addObject("error",e);
//        return modelAndView;
//    }
//    @ExceptionHandler(ArithmeticException.class)
//    public ModelAndView arithmeticExeption(Exception e){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("error2");
//        modelAndView.addObject("error",e);
//        return modelAndView;
//    }
}
