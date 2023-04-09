package com.lanqiao.jd.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanqiao.jd.entity.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author 袁佳林
 * @Description
 * @Date 2023/4/7 16:21
 */
@Mapper
public interface OneMapper extends BaseMapper<Product> {
}
