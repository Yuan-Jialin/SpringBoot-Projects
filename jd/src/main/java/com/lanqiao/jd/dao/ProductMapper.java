package com.lanqiao.jd.dao;

import com.lanqiao.jd.entity.DetailsPageSelect;
import com.lanqiao.jd.entity.ProdBusiComm;
import com.lanqiao.jd.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductMapper {
    int deleteByPrimaryKey(Integer productId);
    int insert(Product record);
    int insertSelective(Product record);
    Product selectByPrimaryKey(Integer productId);
    int updateByPrimaryKeySelective(Product record);
    int updateByPrimaryKey(Product record);
    List<Product> selectAllProductByBusinessId(Integer businessId);
    //Add
    //后台->模糊查询
    List<Product> selectByProductNameBack(Product product);
    //模糊查询->主页搜索栏搜索商品
    List<ProdBusiComm> fuzzyQueryProduct(String name);
    //商品详情页->1
    DetailsPageSelect detailPage(int productId);
    //根据productId查询所有商品的url
    List<String> selectImgUrl(Integer productId);

}