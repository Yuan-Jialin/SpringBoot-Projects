package com.lanqiao.jd.service.impl;

import com.lanqiao.jd.dao.UserAddressMapper;
import com.lanqiao.jd.entity.UserAddress;
import com.lanqiao.jd.service.UserAddressService;
import com.lanqiao.jd.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAddressServiceImpl implements UserAddressService {
    @Autowired
    UserAddressMapper userAddressMapper;

    @Override
    public Result insertUserAddress(UserAddress userAddress) {
        try{
            if(userAddressMapper.insertSelective(userAddress) > 0){
                return Result.createSuccessResult();
            }else{
                return Result.createByFailure("插入失败！");
            }
        }catch (Exception e){
            return Result.createByFailure("出现错误联系，联系管理员");
        }
    }

    @Override
    public Result deleteUserAddress(int userAddressId) {
        try{
            if(userAddressMapper.deleteByPrimaryKey(userAddressId) > 0){
                return Result.createSuccessResult();
            }else{
                return Result.createSuccessResult("删除失败，没有此信息");
            }
        }catch (Exception e){
            return Result.createByFailure("出现错误,联系管理员");
        }
    }

    @Override
    public Result getAddress(int userId) {
        try{
            List<UserAddress> userAddressList = userAddressMapper.selectByUserId(userId);
            return Result.createSuccessResult(userAddressList.size(),userAddressList);
        }catch (Exception e){
            return Result.createByFailure("出现错误,联系管理员");
        }
    }

}
