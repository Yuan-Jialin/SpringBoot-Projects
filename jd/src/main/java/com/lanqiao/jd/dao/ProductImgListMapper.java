package com.lanqiao.jd.dao;

import com.lanqiao.jd.entity.ProductImgList;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductImgListMapper {
    int deleteByPrimaryKey(Integer imgId);

    int insert(ProductImgList record);

    int insertSelective(ProductImgList record);

    ProductImgList selectByPrimaryKey(Integer imgId);

    int updateByPrimaryKeySelective(ProductImgList record);

    int updateByPrimaryKey(ProductImgList record);

    List<ProductImgList> selectByItemId(Integer itemId);

}