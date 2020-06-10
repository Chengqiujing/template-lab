package com.chengqj.springbootsenior.controller;

import com.chengqj.springbootsenior.pojo.Users;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.validation.Valid;

@Controller
public class UsersController {
    /**
     * 跳转用户添加
     * @return
     * 注意：这里加参数的原因是要配合thymeleaf异常机制，因为springMVC默认会将参数保存到Model中传递。
     *       key的名字会使用该对象的驼峰式的命名规则来作为key（也就是会把类名的第一个首字母小写作为key）
     * 可以用@ModelAttribute 来修改key的名字
     */
    @RequestMapping("/addUser")
    public String addUserss(@ModelAttribute("aa") Users u){
        return "addUser";
    }

    /**
     * 用户添加
     * @Valid 开启数据校验 hibernate-validate校验规则
     * BindingResult:封装了校验结果（还需要详细了解这个对象）
     *
     * 几个需要注意的注解
     * @NotBlank 判断字符串是否为null或者式空串（去掉首位空格）
     * @NotEmpty 同上，但不会去掉首位空格
     * @Length 来判断字符串的长度（最大或者最小）
     * @Min 整形数值最小为多少
     * @Max 整型数值最大是多少
     * @Email 邮箱类型的判断 可以填写正则表达式
     */
    @RequestMapping("/save")
    public String saveUser(@ModelAttribute("aa") @Valid Users users, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "addUser";
        }
        System.out.println(users);
        return "ok";
    }

    //测试全局异常
    @RequestMapping("/testException")
    public String testException(){
        String str = null;
        str.length();
        return "ok";
    }
    //测试全局异常
    @RequestMapping("/testException2")
    public String testException2(){
        int a = 10/0;
        return "ok";
    }



}
