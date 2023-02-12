package com.lanqiao.jd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lanqiao.jd.dao")
public class Jd1Application {

    public static void main(String[] args) {
        SpringApplication.run(Jd1Application.class, args);
    }

}
