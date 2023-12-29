package com.sky.mapper;

import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author 袁佳林
 * @Description
 * @Date 2023/12/28 12:24
 */
@Mapper
public interface OrderMapper {
    void insert(Orders orders);
}
