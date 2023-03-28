package com.example.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.reggie.entity.Category;
import com.example.reggie.mapper.CategoryMapper;
import com.example.reggie.service.CategoryService;
import org.springframework.stereotype.Service;

/**
 * @Author 袁佳林
 * @Description
 * @Date 2023/3/28 15:14
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
