package com.yjl.controller;

import com.yjl.pojo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: DLMU 袁佳林
 * @Date: 2023/11/13 09:12
 * @Description:
 **/
@RestController
@RequestMapping("/article")
public class ArticleController {
    @GetMapping("/list")
    public Result<String>list(){

        return Result.success("无产阶级万岁");
    }

}