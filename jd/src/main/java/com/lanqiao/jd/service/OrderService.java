package com.lanqiao.jd.service;

import com.lanqiao.jd.entity.Order;
import com.lanqiao.jd.entity.OrderVo;
import com.lanqiao.jd.util.Result;

/**
 * @Author 袁佳林
 * @Description
 * @Date 2023/1/9 9:22
 */
public interface OrderService {
    //向订单表和订单详情表中插入记录
    Result insertOrder(OrderVo orderVo);

    //支付->检查用户余额->减少用户余额->更改订单状态为已支付
    Result pay(Order order);

    //过度页面
    Result showItem(int []cartItemIdArray);

    //查询所有的订单信息
    Result getAllOrderByUserId(int userId);

    public Result getOrderByOrderId(int orderId);

    public Result deleteOrder(int orderId);
}
