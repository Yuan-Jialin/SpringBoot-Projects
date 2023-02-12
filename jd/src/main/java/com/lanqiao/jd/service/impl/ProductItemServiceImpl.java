package com.lanqiao.jd.service.impl;

import com.lanqiao.jd.dao.ProductItemMapper;
import com.lanqiao.jd.entity.ProductItem;
import com.lanqiao.jd.service.ProductItemService;
import com.lanqiao.jd.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductItemServiceImpl implements ProductItemService {
    @Autowired
    ProductItemMapper productItemMapper;
    @Override
    public Result insertProductItem(ProductItem productItem) {
        try{

            int col = productItemMapper.insertSelective(productItem);
            if (col>0){
                return Result.createSuccessResult();
            }else {
                return Result.createByFailure("插入数据库错误");
            }
        }catch (Exception e){
            return  Result.createByFailure("出现错误，联系管理员");
        }
    }

    @Override
    public Result deleteProductItem(int productItemId) {
        try {
            int col = productItemMapper.deleteByPrimaryKey(productItemId);
            if(col > 0){
                return Result.createSuccessResult();
            }else{
                return Result.createByFailure("没有该条目");
            }
        }catch (Exception e){
            return Result.createByFailure("出现错误，联系管理员");
        }
    }

    @Override
    public Result changeProductItem(ProductItem productItem) {
        try {
            int col = productItemMapper.updateByPrimaryKeySelective(productItem);
            if(col > 0){
                return Result.createSuccessResult();
            }else{
                return Result.createByFailure("修改失败");
            }
        }catch (Exception e){
            return Result.createByFailure("出现错误，联系管理员");
        }
    }

    @Override
    public Result selectProductItemByProductId(int productId) {
        try {
            ProductItem  productItem= productItemMapper.selectByProductId(productId);
            return Result.createSuccessResult(productItem);
        }catch (Exception e){
            return Result.createByFailure("查询异常");
        }
    }

}
