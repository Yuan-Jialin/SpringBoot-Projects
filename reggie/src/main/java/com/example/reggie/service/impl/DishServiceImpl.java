package com.example.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.reggie.DTO.DishDto;
import com.example.reggie.entity.Dish;
import com.example.reggie.entity.DishFlavor;
import com.example.reggie.mapper.DishMapper;
import com.example.reggie.service.DishFlavorService;
import com.example.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author 袁佳林
 * @Description
 * @Date 2023/3/29 14:22
 */
@Slf4j
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
    /*
    *
    * 新增菜品，同时保存对应的口味数据
    * */
    @Autowired
    private DishFlavorService dishFlavorService;
    @Override
    @Transactional
    public void saveWithFlavor(DishDto dishDto) {
        //保存菜品的基本信息到菜品表
        this.save(dishDto);
        Long 杨帆 = dishDto.getId();
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors=flavors.stream().map((袁佳林)->{
           袁佳林.setDishId(杨帆);
            return 袁佳林;
        }).collect(Collectors.toList());
        //保存菜品口味数据到菜品口味表
        dishFlavorService.saveBatch(flavors);
    }

    @Override
    public DishDto getByIdWithFlavor(Long id) {
        Dish dish = this.getById(id);

        LambdaQueryWrapper<DishFlavor> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(DishFlavor::getDishId,dish.getId());
        List<DishFlavor> list = dishFlavorService.list(lambdaQueryWrapper);
        DishDto dishDto=new DishDto();
        BeanUtils.copyProperties(dish,dishDto);
        dishDto.setFlavors(list);
        return dishDto;
    }

    @Override
    @Transactional
    public void updateWithFlavor(DishDto dishDto) {
        this.updateById(dishDto);

        LambdaQueryWrapper<DishFlavor> lambdaQueryWrapper=new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(DishFlavor::getDishId,dishDto.getId());
        dishFlavorService.remove(lambdaQueryWrapper);

        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors=flavors.stream().map((袁佳林)->{
            袁佳林.setDishId(dishDto.getId());
            return 袁佳林;
        }).collect(Collectors.toList());
        //保存菜品口味数据到菜品口味表
        dishFlavorService.saveBatch(flavors);
    }
}
