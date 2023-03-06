package com.lanqiao.jd.controller.back.bussness;

import com.lanqiao.jd.entity.Business;
import com.lanqiao.jd.service.BusinessService;
import com.lanqiao.jd.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//后台->商家管理页面
@RestController
@RequestMapping("/back")
public class BusinessController {
    @Autowired
    BusinessService businessService;

    //后台->添加商家
    @PostMapping("/insertBusiness")
    public Result insertBusiness(Business business){
        return businessService.insertBusiness(business);
    }

    //后台->删除商家
    @PostMapping("/deleteBusiness")
    public Result deleteBusiness(int businessId)
    {
        return businessService.deleteBusiness(businessId);
    }

    //后台->更改商家信息
    @PostMapping("/updateBusiness")
    public Result updateBusiness(Business business){
        return businessService.updateBusiness(business);
    }

    //后台->查询所有商家的信息
    @PostMapping("/selectAllBusiness")
    public Result selectAll(){
        return businessService.selectAllBusiness();
    }

    //后台->根据id查询商家信息
    @PostMapping("/selectBusinessById")
    public  Result selectByBusinessId(int businessId){
        return businessService.selectBusinessById(businessId);
    }
}
