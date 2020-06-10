package com.chengqj.springbootsenior;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching  //使用缓存
@MapperScan("com.chengqj.springbootsenior.dao.mapper")
@EnableScheduling
public class SpringbootSeniorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSeniorApplication.class, args);
    }

}
