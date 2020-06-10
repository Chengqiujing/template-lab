package com.chengqj.springbootsenior.pojo;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 这里实现Serializable接口时因为 开始缓存后pojo必须要实现此接口 以便磁盘缓存
 */
public class Users implements Serializable {
    @NotBlank(message="用户名不能为空") //非空校验
    private String name;
    @NotBlank(message = "密码不能为空") //非空校验
    @Length(min = 2,max = 6,message = "必须在2位和6位之间")
    private String password;
    @Min(15)
    private Integer age;
    @Email
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Users{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
