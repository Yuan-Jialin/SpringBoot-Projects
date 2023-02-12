package com.lanqiao.jd.service;

import com.lanqiao.jd.entity.ProductItem;
import com.lanqiao.jd.util.Result;

public interface ProductItemService {

    //插入productItem
    Result insertProductItem(ProductItem productItem);

    //删除productItem

    Result deleteProductItem(int productItemId);

    //修改productItem
    Result changeProductItem(ProductItem productItem);

    //查询productItme

    Result selectProductItemByProductId(int productId);


}
