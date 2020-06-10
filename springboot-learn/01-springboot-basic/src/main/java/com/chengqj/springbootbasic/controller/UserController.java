package com.chengqj.springbootbasic.controller;

import com.chengqj.springbootbasic.pojo.Users;
import org.apache.tomcat.util.digester.ArrayStack;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * springboot整合jsp
 */
@Controller
public class UserController {
    /*
    jsp的访问
    它是访问ServletContextPath路径（即webapp目录）下的jsp页面 一定要配置前缀和后缀
     */
    @RequestMapping("/showUser")
    public String showUser(Model model){
        List<Users> list = new ArrayStack<>();
        list.add(new Users(1,"zhangsan",10));
        list.add(new Users(2,"lisi",12));
        list.add(new Users(3,"wangwu",13));
        model.addAttribute("list",list);
        return "userList";
    }
    /*
    FreeMarker的访问
    注意一定要配置文件后缀名 不然访问不到
    模板数据一定要放到 resources/templates下 这是springboot的默认设置
    同理静态数据一定要放到resources/static下，静态数据springboot的查找范围在contextPath,static两个目录，自然也可以自定义目录进行配置
     */
    @RequestMapping("/showUserFreeMarker")
    public String showUserFreeMarker(Model model) {
        List<Users> list = new ArrayStack<>();
        list.add(new Users(1, "zhangsan", 10));
        list.add(new Users(2, "lisi", 12));
        list.add(new Users(3, "wangwu", 13));
        model.addAttribute("list", list);
        return "userListFreeMarker";
    }
}
