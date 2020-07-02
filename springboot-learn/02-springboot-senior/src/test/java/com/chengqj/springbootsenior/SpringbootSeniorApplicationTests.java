package com.chengqj.springbootsenior;

import com.chengqj.springbootsenior.pojo.Users;
import com.chengqj.springbootsenior.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@SpringBootTest
class SpringbootSeniorApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private UserService userService;
    //ehcache：基本测试
    @Test
    public void test1(){
        System.out.println(userService.selectAll());
        System.out.println(userService.selectAll());
    }

    //ehcache：包含key的测试
    @Test
    public void test2(){
        System.out.println(userService.selectOne("1"));
        System.out.println(userService.selectOne("1"));

    }


    //ehcache：基本测试
    @Test
    @CacheEvict
    public void test3(){
        System.out.println(userService.selectAll());

        Users users = new Users();
        users.setAge(123);
        users.setEmail("sadsfadsf");
        users.setName("测试测试");
        users.setPassword("1231231");
        userService.saveUser(users);
        System.out.println(userService.selectAll());
    }


    /**
     * redis测试
     */
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    //测试字符串
    @Test
    public void test4(){
       redisTemplate.opsForValue().set("str","chengqj");

       String str = (String) redisTemplate.opsForValue().get("str");
        System.out.println(str);
    }

    //测试实体对象
    @Test
    public void test5(){
        Users user = new Users();
        user.setPassword("123");
        user.setName("小王");
        user.setEmail("123@123.com");
        user.setAge(12);
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer(Users.class));
        redisTemplate.opsForValue().set("xiaowang",user);
        Users u = (Users)redisTemplate.opsForValue().get("xiaowang");
        System.out.println(u);
    }
    @Autowired
    StringRedisTemplate srt;
    @Test
    public void test6(){
        srt.opsForValue().set("string","string");
    }

}
