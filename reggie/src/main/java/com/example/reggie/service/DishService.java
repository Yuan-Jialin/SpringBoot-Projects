package com.example.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.reggie.DTO.DishDto;
import com.example.reggie.entity.Dish;

/**
 * @Author 袁佳林
 * @Description
 * @Date 2023/3/29 14:21
 */
public interface DishService extends IService<Dish> {
    public void saveWithFlavor(DishDto dishDto);
    //通过Id查询对应的菜品信息和口味信息
    public DishDto getByIdWithFlavor(Long id);

    public void updateWithFlavor(DishDto dishDto);

}
