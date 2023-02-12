package com.lanqiao.jd.service;

import com.lanqiao.jd.entity.CartItem;
import com.lanqiao.jd.util.Result;

public interface CartItemService {
    //向购物车插入一条信息
    Result insertCartItem(int userId, CartItem cartItem);

    //删除一条信息
    Result deleteCartItem(int userId, int productId);

    //展示购物车信息
    Result showCartItem(int userId);

    //增加数量
    Result addCartItemNum(int userId,int productId);

    //减少数量
    Result subCartItemNum(int userId,int productId);

    //在生成订单时删除购物车信息
    Result deleteWhenCreateOrder(int []cartItemIdList);

    //查询用户的购物车商品数量
    Result getCartNum(int userId);

}
