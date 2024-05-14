package com.heima.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: DLMU 袁佳林
 * @Date: 2024/5/13 21:12
 * @Description:
 **/
@SpringBootApplication
//用于集成注册中心
@EnableDiscoveryClient
@MapperScan("com.yjl.user.mapper")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);
    }
}
