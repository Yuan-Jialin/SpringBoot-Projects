package com.lanqiao.jd.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanqiao.jd.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author 袁佳林
 * @Description
 * @Date 2023/5/7 21:34
 */
@Mapper
public interface YangMapper extends BaseMapper<Order> {
}
