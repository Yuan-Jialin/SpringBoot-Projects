package com.lanqiao.jd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
/**
 * @Author 袁佳林
 * @Description
 * @Date 2022/12/31 12:59
 */
@SpringBootApplication
@MapperScan("com.lanqiao.jd.dao")
public class Jd1Application {

    public static void main(String[] args) {
        SpringApplication.run(Jd1Application.class, args);
    }

}
