package com.lanqiao.jd.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.lanqiao.jd.dao.YangMapper;
import com.lanqiao.jd.entity.*;
import com.lanqiao.jd.service.OrderService;
import com.lanqiao.jd.service.UserAddressService;
import com.lanqiao.jd.service.UserService;
import com.lanqiao.jd.service.YangService;
import com.lanqiao.jd.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 袁佳林
 * @Description
 * @Date 2023/5/7 21:29
 */
@RestController
@RequestMapping("/yang")
public class YangController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private YangService yangService;
    @Autowired
    private UserAddressService userAddressService;
    @GetMapping("/fan")
    public Result<List<YJLOrder>> getorder(){

        LambdaQueryWrapper<Order>lambdaQueryWrapper=new LambdaQueryWrapper<>();
        List<Order> list = yangService.list();
        List<YJLOrder>ans=new ArrayList<>();
        for(int i=0;i< list.size();i++)
        {
            System.out.println(list.get(i).toString());
            YJLOrder yjlOrder=new YJLOrder();
            yjlOrder.setId(list.get(i).getOrderId());
            yjlOrder.setStatus(list.get(i).getOrderStatus());
            yjlOrder.setPrice(list.get(i).getTotalPrice());
            User userById = userService.findUserById(list.get(i).getUserId());
            yjlOrder.setUsername(userById.getUserName());
            UserAddress addressByAddressId = userAddressService.getAddressByAddressId(list.get(i).getUserAddressId());
            yjlOrder.setAddress(addressByAddressId.getAddress());
            System.out.println(yjlOrder);
            ans.add(yjlOrder);

        }

        return  Result.createSuccessResult(ans.size(),ans);
    }
    @PostMapping("/delete")
    public Result delete(int id)
    {
        orderService.deleteOrder(id);
        return Result.createByFailure("成功");
    }

    @PostMapping("/fahuo")
    public Result yangfan(Integer id)
    {
        Order byId = yangService.getById(id);
        byId.setOrderStatus("发货中");
        yangService.updateById(byId);
        return Result.createSuccessResult("成功");
    }


}
