package com.example.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.reggie.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author 袁佳林
 * @Description
 * @Date 2023/3/29 14:19
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
