package com.chengqj.springbootbasic.controller;

import com.chengqj.springbootbasic.pojo.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * thymeleaf 入门
 */
@Controller
public class ThymeleafController {

    @RequestMapping("/showThymeleaf")
    public String showInfo(HttpServletRequest request,Model model){
        //字符串
        model.addAttribute("msg","Thymeleaf第一个案例");
        //日期
        model.addAttribute("date",new Date());
        //条件-if
        model.addAttribute("sex","男");
        //条件-switch
        model.addAttribute("id","2");
        //遍历 list
        List<Users> list = new ArrayList<>();
        list.add(new Users(1,"zhangsan",21));
        list.add(new Users(2,"lisi",23));
        list.add(new Users(3,"wangwu",25));
        model.addAttribute("list",list);

        //遍历方法
        Map<String,Users> map = new HashMap<>();
        map.put("u1",new Users(1,"zhangsan",21));
        map.put("u2",new Users(2,"lisi",23));
        map.put("u3",new Users(1,"zhangsan",21));
        model.addAttribute("map",map);

        //获取作用域对象
        request.setAttribute("req","HttpServletRequest");
        request.getSession().setAttribute("sess","HttpSession");
        request.getSession().getServletContext().setAttribute("app","Application");

        return "index-thymeleaf";
    }

}
