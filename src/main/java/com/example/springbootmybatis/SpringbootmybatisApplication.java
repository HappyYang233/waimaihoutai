package com.example.springbootmybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("com.example.springbootmybatis.mapper")
@SpringBootApplication
@EnableScheduling
public class SpringbootmybatisApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringbootmybatisApplication.class, args);
    }

}
