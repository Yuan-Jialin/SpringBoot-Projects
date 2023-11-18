package com.yjl.service;

import com.yjl.pojo.Article;
import com.yjl.pojo.PageBean;

public interface ArticleService {
    void add(Article article);


    PageBean<Article> list(Integer pageNum, Integer pageSize, String categoryId, String state);

    void update(Article article);

    Article findById(Integer id);

    void delete(Integer id);
}
