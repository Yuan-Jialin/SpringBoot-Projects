package com.lanqiao.jd.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.lanqiao.jd.entity.Order;
import com.lanqiao.jd.entity.YJLOrder;
import com.lanqiao.jd.service.OrderService;
import com.lanqiao.jd.service.YangService;
import com.lanqiao.jd.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private YangService yangService;
    @GetMapping("/fan")
    public List getorder(){
        System.out.println("dasdds");
        LambdaQueryWrapper<Order>lambdaQueryWrapper=new LambdaQueryWrapper<>();
        String p="";
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(p),Order::getOrderStatus,p);
        List<Order> list = yangService.list();

        YJLOrder yjlOrder=new YJLOrder();
        
        return  list;
    }


}
