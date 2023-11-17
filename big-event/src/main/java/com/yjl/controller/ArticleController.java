package com.yjl.controller;

import com.yjl.pojo.Article;
import com.yjl.pojo.Result;
import com.yjl.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: DLMU 袁佳林
 * @Date: 2023/11/13 09:12
 * @Description:
 **/
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @PostMapping
    public Result add(@RequestBody @Validated Article article) {
        articleService.add(article);
        return Result.success();
    }

}
