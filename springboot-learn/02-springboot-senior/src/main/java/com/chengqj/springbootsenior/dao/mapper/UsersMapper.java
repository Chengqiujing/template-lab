package com.chengqj.springbootsenior.dao.mapper;





import com.chengqj.springbootsenior.pojo.Users;

import java.util.List;


public interface UsersMapper {
    void insertUser(Users users);

    List<Users> selectUsersAll();

    Users selectUserById(String id);

    void updateUser(Users users);

    void deleteUserById(String id);
}
