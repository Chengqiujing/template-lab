package com.chengqj.springbootbasic.mybatis.mapper;



import com.chengqj.springbootbasic.mybatis.pojo.Users;

import java.util.List;


public interface UsersMapper {
    void insertUser(Users users);

    List<Users> selectUsersAll();

    Users selectUserById(String id);

    void updateUser(Users users);

    void deleteUserById(String id);
}
