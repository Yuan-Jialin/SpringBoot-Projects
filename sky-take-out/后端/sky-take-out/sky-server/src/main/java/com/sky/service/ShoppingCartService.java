package com.sky.service;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;

import java.util.List;

/**
 * @Author 袁佳林
 * @Description
 * @Date 2023/12/27 10:10
 */
public interface ShoppingCartService {
    void addShoppingCart(ShoppingCartDTO shoppingCartDTO);

    List<ShoppingCart> showShoppingCart();

    void clean();
    void subShoppingCart(ShoppingCartDTO shoppingCartDTO);
}
