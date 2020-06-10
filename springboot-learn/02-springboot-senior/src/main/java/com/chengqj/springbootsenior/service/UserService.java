package com.chengqj.springbootsenior.service;

import com.chengqj.springbootsenior.dao.UserDaoImpl;
import com.chengqj.springbootsenior.dao.mapper.UsersMapper;
import com.chengqj.springbootsenior.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 使用springboot整合Junit
 *
 * @Cacheable(value="users",key="#i")
 *      其实ehcache缓存中存储就是已键值对（key-value）的方式，这里制定了key，当key为同一对象时则从缓存中查找，key指定要加#
 * @CacheEvict
 *      清楚缓存
 *
 */
@Service
public class UserService {
    @Autowired
    private UserDaoImpl userDao;

    public void addUser(){
        userDao.addUsers();

    }

    /**
     * 测试缓存
     */
    @Autowired
    private UsersMapper usersMapper;
    //@Cacheable 表示该方法开启缓存，值为缓存策略
    //开启缓存最重要的点在于一定要熟悉缓存策略
    @Cacheable("users")
    public List<Users> selectAll(){
        return usersMapper.selectUsersAll();
    }

    //其实ehcache缓存中存储就是已键值对（key-value）的方式，这里制定了key，当key为同一对象时则从缓存中查找，key指定要加#
    @Cacheable(value="users",key="#i")
    public Users selectOne(String i){
        return usersMapper.selectUserById(i);
    }

    @CacheEvict(value="users",allEntries = true)
    public void saveUser(Users users){
        usersMapper.insertUser(users);
    }
}

