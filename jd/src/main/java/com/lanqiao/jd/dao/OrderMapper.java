package com.lanqiao.jd.dao;

import com.lanqiao.jd.entity.Cart_Product_Business;
import com.lanqiao.jd.entity.Order;
import com.lanqiao.jd.entity.SelectOrderInfoByUserId;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    Cart_Product_Business showItem(Integer cartItemId);

    //Add -> 查询一个用户的所有订单
    List<SelectOrderInfoByUserId> getAllOrderByUserId(int userId);
}