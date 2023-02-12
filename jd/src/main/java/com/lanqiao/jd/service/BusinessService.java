package com.lanqiao.jd.service;

import com.lanqiao.jd.entity.Business;
import com.lanqiao.jd.util.Result;


public interface BusinessService {
    //后台->添加商家
    Result insertBusiness(Business business);

    //后台->删除商家
    Result deleteBusiness(int businessId);

    //后台->更改商家信息（need：businessId & businessName）
    Result updateBusiness(Business business);

    //后台->根据id查询商品的信息
    Result selectBusinessById(int businessId);

    //后台->查询所有商家的信息
    Result selectAllBusiness();
}
