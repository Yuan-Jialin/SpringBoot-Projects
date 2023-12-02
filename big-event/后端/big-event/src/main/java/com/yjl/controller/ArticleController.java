package com.yjl.controller;

import com.yjl.pojo.Article;
import com.yjl.pojo.PageBean;
import com.yjl.pojo.Result;
import com.yjl.service.ArticleService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.AnnotatedType;

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
    public Result add(@RequestBody @Validated(Article.add.class) Article article) {
        articleService.add(article);
        return Result.success();
    }

    @GetMapping
    public Result<PageBean<Article>>list(Integer pageNum,Integer pageSize,@RequestParam(required = false) String categoryId,@RequestParam(required = false) String state){
        PageBean<Article>yjl= articleService.list(pageNum,pageSize,categoryId,state);
        return Result.success(yjl);
    }

    @PutMapping
    public Result update(@RequestBody @Validated(Article.update.class) Article article ){
        articleService.update(article);
        return Result.success();
    }
    @GetMapping("/detail")
    public Result<Article>detail(Integer id){
        Article article=articleService.findById(id);
        return Result.success(article);
    }
    @DeleteMapping
    public Result delete(Integer id){
        articleService.delete(id);
        return Result.success();
    }

}
