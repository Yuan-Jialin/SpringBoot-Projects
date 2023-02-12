package com.lanqiao.jd.service.impl;

import com.lanqiao.jd.dao.CartItemMapper;
import com.lanqiao.jd.dao.ShopCartMapper;
import com.lanqiao.jd.entity.CartItem;
import com.lanqiao.jd.entity.Cart_Product_Business;
import com.lanqiao.jd.entity.ShopCart;
import com.lanqiao.jd.service.CartItemService;
import com.lanqiao.jd.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    CartItemMapper cartItemMapper;
    @Autowired
    ShopCartMapper shopCartMapper;


    @Override
    public Result insertCartItem(int userId, CartItem cartItem) {
        try{
            int shopCartId = shopCartMapper.selectByUserId(userId).getShopCartId();
            //判断购物车中是否有该商品
            List<CartItem> cartItemList = cartItemMapper.selectByShopCartId(shopCartId);
            int flag = -1;
            CartItem orginalCartItem = new CartItem();
            for (CartItem check:cartItemList) {
                if(check.getProductId() == cartItem.getProductId()){
                    orginalCartItem = check;
                    flag = check.getNum();
                    break;
                }
            }
            if(flag > 0){
                orginalCartItem.setNum(cartItem.getNum()+flag);
                if(cartItemMapper.updateByPrimaryKeySelective(orginalCartItem) > 0){
                    return Result.createSuccessResult();
                }else {
                    return Result.createByFailure("在增加商品数量时失败!");
                }
            }else {
                cartItem.setShopCartId(shopCartId);
                if (cartItemMapper.insertSelective(cartItem) > 0) {
                    return Result.createSuccessResult();
                } else {
                    return Result.createByFailure("插入失败！");
                }
            }
        }catch (Exception e){
            return Result.createByFailure("出现错误，联系管理员！");
        }
    }

    @Override
    public Result deleteCartItem(int userId, int productId) {
        try {
            int shopCartId = shopCartMapper.selectByUserId(userId).getShopCartId();
            CartItem cartItem = cartItemMapper.selectByProductId(shopCartId,productId);
            if(cartItemMapper.deleteByPrimaryKey(cartItem.getCartItemId()) > 0){
                return Result.createSuccessResult();
            }else {
                return Result.createByFailure("删除失败！");
            }

        }catch (Exception e){
            return Result.createByFailure("出现错误，联系管理员！");
        }
    }

    @Override
    public Result showCartItem(int userId) {
        try{

            List<Cart_Product_Business> cart_product_businessList = cartItemMapper.selectCartItemByuserId(userId);
            System.out.println("showCartItem:success");
            return Result.createSuccessResult(cart_product_businessList.size(),cart_product_businessList);
        }catch (Exception e){
            return Result.createByFailure("出现错误，联系管理员！");
        }
    }

    @Override
    public Result addCartItemNum(int userId, int productId) {
        try {
            CartItem cartItem = cartItemMapper.selectByUserIdAndProductId(userId,productId);
            cartItem.setNum(cartItem.getNum()+1);
            if(cartItemMapper.updateByPrimaryKeySelective(cartItem) > 0){
                return Result.createSuccessResult();
            }else {
                return Result.createByFailure("增加数量失败!");
            }
        } catch (Exception e) {
            return Result.createByFailure("出现错误，联系管理员！");
        }
    }

    @Override
    public Result subCartItemNum(int userId, int productId) {
        try {
            CartItem cartItem = cartItemMapper.selectByUserIdAndProductId(userId,productId);
            cartItem.setNum(cartItem.getNum()-1);
            if(cartItemMapper.updateByPrimaryKeySelective(cartItem) > 0){
                return Result.createSuccessResult();
            }else {
                return Result.createByFailure("增加数量失败!");
            }
        } catch (Exception e) {
            return Result.createByFailure("出现错误，联系管理员！");
        }
    }

    @Override
    public Result deleteWhenCreateOrder(int[] cartItemIdList) {
        try {
            for (int i:cartItemIdList) {
                if(cartItemMapper.deleteByPrimaryKey(i)<0){
                    return Result.createByFailure("删除购物车相关信息失败！");
                }
            }
            return Result.createSuccessResult();
        } catch (Exception e) {
            return Result.createByFailure("删除时发生异常！");
        }
    }

    @Override
    public Result getCartNum(int userId) {
        try {
            //根据userId查询shopCartId
            ShopCart shopCart = shopCartMapper.selectByUserId(userId);
            //根据shopCartId查询cartItem
            List<CartItem> cartItems= cartItemMapper.selectByShopCartId(shopCart.getShopCartId());
            return Result.createSuccessResult(cartItems.size());
        }catch (Exception e){
            return Result.createByFailure("出现错误");
        }

    }
}
