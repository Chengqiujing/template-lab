package com.chengqj.springbootbasic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan//在springBoot启动时，会扫描@WebServlet注解，并将该类实例化
@MapperScan("com.chengqj.springbootbasic.mybatis.mapper")//扫描mybatis mapper接口
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
