package com.yjl.controller;

import com.yjl.pojo.Category;
import com.yjl.pojo.Result;
import com.yjl.service.CategoryService;
import com.yjl.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
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
    public Result add(@RequestBody @Validated(Category.Add.class) Category category){
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        Map<String,Object> map = ThreadLocalUtil.get();
        category.setCreateUser((Integer) map.get("id"));
        categoryService.add(category);
        return Result.success();
    }

    @GetMapping
    public Result<List<Category>>list(){
        List<Category>cs=categoryService.list();
        return Result.success(cs);

    }
    @GetMapping("/detail")
    public Result<Category>detail(Integer id){
            return Result.success(categoryService.findById(id));
    }

    @PutMapping
    public Result update(@RequestBody @Validated(Category.Update.class) Category category){
        categoryService.update(category);
        return Result.success();
    }

    @DeleteMapping("/delete")
    public Result delete(Integer id){
        categoryService.delete(id);
        return Result.success();
    }

}
