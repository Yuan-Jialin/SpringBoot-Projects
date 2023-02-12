package com.lanqiao.jd.service;

import com.lanqiao.jd.entity.ProductImgList;
import com.lanqiao.jd.util.Result;

public interface ProductImgListService {
    //增加新记录
    Result insertImgList(ProductImgList productImgList);

    //删除记录
    Result deleteImgListById(int imgId);

    //修改新纪录
    Result changeImgList(String url,int imgId);

    //通过productItemId 查看商品信息
    Result selectAllImgList(int itemId);
}
