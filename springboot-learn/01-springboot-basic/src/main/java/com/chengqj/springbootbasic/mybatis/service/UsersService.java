package com.chengqj.springbootbasic.mybatis.service;


import com.chengqj.springbootbasic.mybatis.mapper.UsersMapper;
import com.chengqj.springbootbasic.mybatis.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UsersService {
    @Autowired
    private UsersMapper usersMapper;

    public void addUsers(Users users){
        usersMapper.insertUser(users);
    }

    public List<Users> findAllUsers(){
        return usersMapper.selectUsersAll();
    }

    public Users findUserById(String id){
        Users user = usersMapper.selectUserById(id);
        return user;
    }

    public void updateUserById(Users users){
        usersMapper.updateUser(users);
    }

    public void deleteUserById(String id){
        usersMapper.deleteUserById(id);
    }
}
