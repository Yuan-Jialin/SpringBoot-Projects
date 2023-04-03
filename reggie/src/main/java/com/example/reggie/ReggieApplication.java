package com.example.reggie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/*
* 只有加了ServletComponentScan 才会去扫描过滤器这样的类，将其通过spring来托管
* 也可以直接在filter类上加component
* */
@Slf4j
@SpringBootApplication
//@ServletComponentScan
@EnableTransactionManagement
public class ReggieApplication {

    public static void main(String[] args) {

        SpringApplication.run(ReggieApplication.class, args);
        log.info("项目启动！");
    }

}
