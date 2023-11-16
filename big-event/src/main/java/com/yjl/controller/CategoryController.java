package com.yjl.controller;

import com.yjl.pojo.Category;
import com.yjl.pojo.Result;
import com.yjl.service.CategoryService;
import com.yjl.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @Author: DLMU 袁佳林
 * @Date: 2023/11/16 10:40
 * @Description:
 **/
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping
    public Result add(@RequestBody Category category){
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        Map<String,Object> map = ThreadLocalUtil.get();
        category.setCreateUser((Integer) map.get("id"));
        categoryService.add(category);
        return Result.success();
    }
}
