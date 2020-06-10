package com.chengqj.springbootsenior.dao;

import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl {
    //配合数据校验的方法
    public void addUsers(){
        System.out.println("userDaoImpl->getUsers run");
    }
}
