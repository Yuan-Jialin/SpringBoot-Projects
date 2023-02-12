package com.lanqiao.jd.service;


import com.lanqiao.jd.entity.Product;
import com.lanqiao.jd.util.Result;

public interface ProductService {
    //添加product
    Result insertProduct(Product product);

    //删除product
    Result deleteProduct(int productId);

    //查看product
    Result selectProduct(int businessId);

    //根据商家id和商品名称模糊查询
    Result selectByProductNameBack(Product product);

    //多表连接模糊查询->商品首页搜索栏
    Result fuzzyQueryProduct(String name);

    //商品详情页面
    Result productItem(int userId, int productId);

}
