package com.example.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.reggie.entity.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author 袁佳林
 * @Description
 * @Date 2023/3/28 15:12
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
