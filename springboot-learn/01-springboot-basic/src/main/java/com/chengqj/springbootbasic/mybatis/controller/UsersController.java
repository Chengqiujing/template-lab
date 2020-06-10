package com.chengqj.springbootbasic.mybatis.controller;

import com.chengqj.springbootbasic.mybatis.pojo.Users;
import com.chengqj.springbootbasic.mybatis.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;
    /**
     * 页面跳转,这里只是测试，这种做法在跳转功能很不合适
     */
    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page){
        return page;
    }

    @RequestMapping("/addUser")
    public String addUser(Users users){
        usersService.addUsers(users);
        return "ok";
    }
    @RequestMapping("/findUsers")
    public String findUsers(Model model){
        List<Users> allUsers = usersService.findAllUsers();
        model.addAttribute("users",allUsers);
        System.out.println(allUsers);
        return "showUsers";
    }

    @RequestMapping("/findUserById")
    public String findUserById(String id,Model model){
        Users userById = usersService.findUserById(id);
        model.addAttribute("user",userById);
        return "update";
    }

    @RequestMapping("/editUser")
    public String updateUserById(Users users){
        usersService.updateUserById(users);
        return "redirect:findUsers";
    }

    @RequestMapping("/deleteUserById")
    public String deleteUserById(String id){
        usersService.deleteUserById(id);
        return "redirect:findUsers";
    }
}
