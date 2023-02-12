package com.lanqiao.jd.dao;

import com.lanqiao.jd.entity.Business;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BusinessMapper {
    int deleteByPrimaryKey(Integer businessId);

    int insert(Business record);

    int insertSelective(Business record);

    Business selectByPrimaryKey(Integer businessId);

    int updateByPrimaryKeySelective(Business record);

    int updateByPrimaryKey(Business record);

    //ADD
    List<Business> selectAllBusiness();

}