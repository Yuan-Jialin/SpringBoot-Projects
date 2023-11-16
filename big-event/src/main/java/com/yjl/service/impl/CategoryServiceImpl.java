package com.yjl.service.impl;

import com.yjl.mapper.CategoryMapper;
import com.yjl.pojo.Category;
import com.yjl.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: DLMU 袁佳林
 * @Date: 2023/11/16 10:41
 * @Description:
 **/
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public void add(Category category) {
        categoryMapper.add(category);
    }
}
