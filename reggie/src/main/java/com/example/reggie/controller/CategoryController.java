package com.example.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.reggie.common.R;
import com.example.reggie.entity.Category;
import com.example.reggie.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;


/**
 * @Author 袁佳林
 * @Description
 * @Date 2023/3/28 15:15
 */
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    /*
    * 新增分类
    * */
    @PostMapping
    public R<String> save(@RequestBody Category category) {
        log.info("category:{}", category);
        categoryService.save(category);
        return R.success("添加分类成功");
    }
    /*
    * 分类的分页查询
    *
    * */
    @GetMapping("/page")
    public R<Page>page(int page,int pageSize){
        System.out.println(page+" uuuu"+pageSize);
        Page<Category>pageInfo=new Page<>(page,pageSize);
        LambdaQueryWrapper<Category>queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Category::getSort);
        categoryService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);

    }
    /*
    * 根据id删除
    * */
    @DeleteMapping
    public  R<String>delete(@RequestParam("ids") long id){
        log.info("删除分类，id为{}",id);
        categoryService.remove(id);
        return R.success("分类信息删除成功");
    }
    /*
    * 根据id修改分类信息
    * */
    @PutMapping
    public R<String>update(@RequestBody Category category){
        log.info("修改的分类信息为：{}",category);
        categoryService.updateById( category);
        return R.success("修改分类成功");
    }
}
