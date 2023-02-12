package com.lanqiao.jd.dao;

import com.lanqiao.jd.entity.ShopCart;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
@Mapper
@Repository
public interface ShopCartMapper {
    int deleteByPrimaryKey(Integer shopCartId);

    int insert(ShopCart record);

    int insertSelective(ShopCart record);

    ShopCart selectByPrimaryKey(Integer shopCartId);

    int updateByPrimaryKeySelective(ShopCart record);

    int updateByPrimaryKey(ShopCart record);

    ShopCart selectByUserId(Integer userId);
}