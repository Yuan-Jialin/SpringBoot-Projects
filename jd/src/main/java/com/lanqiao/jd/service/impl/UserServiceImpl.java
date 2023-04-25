package com.lanqiao.jd.service.impl;

import com.lanqiao.jd.dao.ShopCartMapper;
import com.lanqiao.jd.dao.UserMapper;
import com.lanqiao.jd.entity.ShopCart;
import com.lanqiao.jd.entity.User;
import com.lanqiao.jd.service.TokenService;
import com.lanqiao.jd.service.UserService;
import com.lanqiao.jd.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * @Author 袁佳林
 * @Description
 * @Date 2023/3/7 16:22
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    ShopCartMapper shopCartMapper;

    @Autowired
    TokenService tokenService;

    @Override
    public Result verifyPhoneNumber(String phoneNumber) {
        try{
            if(userMapper.selectByPhoneNumber(phoneNumber) != null){
                return Result.createByFailure("该手机号已注册，请直接登录！");
            }else {
                return Result.createSuccessResult();
            }
        }catch (Exception e){
            return Result.createByFailure("出现错误，联系管理员");
        }
    }

    //实现注册功能  用户表中插入记录&购物车表中插入记录
    @Override
    @Transactional
    public Result register(User user) {
        //向用户表中插入记录
        String userMd5Pass = tokenService.getMd5(user.getPassword());
        user.setPassword(userMd5Pass);
        int col =  userMapper.insertSelective(user);
        if(col>0){
            int userId = user.getUserId();
            System.out.println(userId + " ");
            //向购物车中插入记录
            ShopCart shopCart = new ShopCart();
            shopCart.setUserId(userId);
            int ret = shopCartMapper.insert(shopCart);
            if(ret > 0){
                return Result.createSuccessResult();
            }
            else{
                return Result.createByFailure("数据库错误");
            }
        }
        else {
            return Result.createByFailure("数据库错误");
        }
    }

    //登录
    @Override
    public Result login(User user) {
        try {
            User record = userMapper.selectUserByUserNameAndPassword(user);
            if(record  != null){
                return Result.createSuccessResult();
            }
            else{
                return Result.createByFailure("用户名或密码错误");
            }
        }catch(Exception e){
            return Result.createByFailure("出现错误，联系管理员");
        }
    }

    @Override
    public User findUserById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public User findByUsername(User user) {
        return userMapper.selectByUserName(user);
    }

    //更新用户余额
    @Override
    public Result addBalance(User user) {
        //查询user
        user.setBalance(user.getBalance().add(userMapper.selectByPrimaryKey(user.getUserId()).getBalance()));
        int col = userMapper.updateByPrimaryKeySelective(user);
       if(col>0){
           return  Result.createSuccessResult();
       }else{
           return Result.createByFailure("出现错误");
       }
    }
}
