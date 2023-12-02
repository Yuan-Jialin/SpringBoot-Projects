package com.yjl.service;

import com.yjl.pojo.Category;

import java.util.List;

/**
 * @Author: DLMU 袁佳林
 * @Date: 2023/11/16 10:40
 * @Description:
 **/

public interface CategoryService {
    void add(Category category);

    List<Category> list();

    Category findById(Integer id);

    void update(Category category);

    void delete(Integer id);
}
