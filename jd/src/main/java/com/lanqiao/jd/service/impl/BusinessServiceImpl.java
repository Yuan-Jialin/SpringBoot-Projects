package com.lanqiao.jd.service.impl;

import com.lanqiao.jd.dao.BusinessMapper;
import com.lanqiao.jd.entity.Business;
import com.lanqiao.jd.service.BusinessService;
import com.lanqiao.jd.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BusinessServiceImpl  implements BusinessService {
    @Autowired
    BusinessMapper businessMapper;

    // 后台->添加商家
    @Override
    public Result insertBusiness(Business business) {
        try{
            int col = businessMapper.insertSelective(business);
            if (col>0){
                return Result.createSuccessResult();
            }else {
                return Result.createByFailure("数据库错误");
            }
        }catch (Exception e){
            return  Result.createByFailure("出现错误，联系管理员");
        }
    }

    //后台->删除商家（如果此商家已经发布商品，则无法注销-->数据库product表的外键）
    @Override
    public Result deleteBusiness(int businessId) {
        try {
            int col = businessMapper.deleteByPrimaryKey(businessId);
            if(col > 0){
                return Result.createSuccessResult();
            }
            else{
                return Result.createByFailure("数据库错误，可能是商家的ID不正确");
            }
        }catch (Exception e){
            return Result.createByFailure("出现错误，联系管理员");
        }
    }

    //更改商家信息
    @Override
    public Result updateBusiness(Business business) {
        try {
            int col = businessMapper.updateByPrimaryKey(business);
            if(col > 0){
                return Result.createSuccessResult();
            }
            else{
                return Result.createByFailure("数据库错误");
            }
        }catch(Exception e){
            return Result.createByFailure("出现错误，联系管理员");
        }
    }

    //根据id查询商品信息
    @Override
    public Result selectBusinessById(int businessId) {
        try{
            Business business;
            business = businessMapper.selectByPrimaryKey(businessId);
            if(business == null){
                return Result.createByFailure("查询错误，可能是id不存在");
            }else{
                return Result.createSuccessResult(business);
            }
        }catch (Exception e){
            return Result.createByFailure("出现错误，联系管理员");
        }

    }

    //查询所有的商家信息
    @Override
    public Result selectAllBusiness() {
        try {
            List<Business> businessList;
            businessList = businessMapper.selectAllBusiness();
            return Result.createSuccessResult(businessList.size(),businessList);
        }catch (Exception e){
            return Result.createByFailure("出现错误，联系管理员");
        }
    }


}
