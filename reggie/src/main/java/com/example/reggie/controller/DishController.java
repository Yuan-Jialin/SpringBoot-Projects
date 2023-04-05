package com.example.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.reggie.DTO.DishDto;
import com.example.reggie.common.R;
import com.example.reggie.entity.Category;
import com.example.reggie.entity.Dish;
import com.example.reggie.service.CategoryService;
import com.example.reggie.service.DishFlavorService;
import com.example.reggie.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author 袁佳林
 * @Description
 * @Date 2023/4/2 12:18
 */
@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishService dishService;
    @Autowired
    private DishFlavorService dishFlavorService;
    @Autowired
    private CategoryService categoryService;
    /*
    * 新增菜品
    * */
    @PostMapping
    public R<String>save(@RequestBody DishDto dishDto){
        dishService.saveWithFlavor(dishDto);
        return R.success("新增菜品成功");
    }
    /*
    * 菜品信息分页查询
    *
    * */
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        Page<Dish> pafeInfo=new Page<>(page,pageSize);
        LambdaQueryWrapper <Dish>lambdaQueryWrapper=new LambdaQueryWrapper();
        lambdaQueryWrapper.like(name!=null,Dish::getName,name);
        lambdaQueryWrapper.orderByDesc(Dish::getUpdateTime);
        dishService.page(pafeInfo,lambdaQueryWrapper);

        //Dist中只有菜品类别的编号，而页面需要名字，DIstDto这个结构中有name
        //所以要返回DishDto
        //现在需要将Dish 转变成DishDto
        //且通过编号查出name
        Page<DishDto> dishDtoPage=new Page<>();
        BeanUtils.copyProperties(pafeInfo,dishDtoPage,"records");
        List<Dish> records = pafeInfo.getRecords();
        //List<DishDto>list;
        List<DishDto> collect = records.stream().map((袁佳林) -> {
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(袁佳林, dishDto);
            Long categoryId = 袁佳林.getCategoryId();
            Category byId = categoryService.getById(categoryId);
            if(byId!=null){
                String name1 = byId.getName();
                dishDto.setCategoryName(name1);
            }
            return dishDto;
        }).collect(Collectors.toList());
        dishDtoPage.setRecords(collect);
        return R.success(dishDtoPage);


    }

    @GetMapping("/{id}")
    public R<DishDto> get(@PathVariable Long id){

        DishDto byIdWithFlavor = dishService.getByIdWithFlavor(id);

        return R.success(byIdWithFlavor);
    }

    /*
    * 修改菜品
    * */
    @PutMapping
    public R<String>update(@RequestBody DishDto dishDto){
        dishService.updateWithFlavor(dishDto);
        return R.success("新增菜品成功");
    }


}
