package com.lanqiao.jd.service.impl;

import com.lanqiao.jd.dao.ProductMapper;
import com.lanqiao.jd.dao.UserAddressMapper;
import com.lanqiao.jd.entity.DetailsPageSelect;
import com.lanqiao.jd.entity.ProdBusiComm;
import com.lanqiao.jd.entity.Product;
import com.lanqiao.jd.entity.UserAddress;
import com.lanqiao.jd.service.OneService;
import com.lanqiao.jd.service.ProductService;
import com.lanqiao.jd.util.FileUtil;
import com.lanqiao.jd.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {


    @Autowired
    ProductMapper productMapper;
    @Autowired
    FileUtil fileUtil;

    @Autowired
    UserAddressMapper userAddressMapper;


    //添加product
    @Override
    public Result insertProduct(Product product) {
        try{

            int col = productMapper.insertSelective(product);
            if (col>0){
                return Result.createSuccessResult();
            }else {
                return Result.createByFailure("插入数据库错误");
            }
        }catch (Exception e){
            return  Result.createByFailure("出现错误，联系管理员");
        }
    }
    //根据producid删除product
    @Override
    public Result deleteProduct(int productId) {
        try{
            Product product =new Product();

            product = productMapper.selectByPrimaryKey(productId);

            String absPath = "F:\\jd\\"+ product.getProductImgUrl();

            File file = new File(absPath);

            System.out.println(absPath);
            if(file.exists()){
                //file.delete();
                System.out.println("删除成功!");
            }
            System.out.println("productID=="+productId);
            int col = productMapper.deleteByPrimaryKey(productId);
            System.out.println("col=="+col);
            if (col>0){
                return Result.createSuccessResult();
            }else {
                return Result.createByFailure("删除错误");
            }
        }catch (Exception e){
            System.out.println(e.toString());
            return  Result.createByFailure("出现错误，联系管理员");
        }
    }


    //根据business查询所有
    @Override
    public Result selectProduct(int productId) {
        try {
            List<Product> productList = productMapper.selectAllProductByBusinessId(productId);

            return Result.createSuccessResult(productList.size(),productList);
        }catch (Exception e){
            return Result.createByFailure("查询异常");
        }

    }

    //后台->模糊查询
    @Override
    public Result selectByProductNameBack(Product product) {
        try{
            List<Product> productList =  productMapper.selectByProductNameBack(product);
            return Result.createSuccessResult(productList.size(),productList);
        }catch (Exception e){
            return Result.createByFailure("出现错误，联系管理员");
        }
    }


    //多表连接模糊查询->主页搜索栏搜索商品
    @Override
    public Result fuzzyQueryProduct(String name) {
        try{
            List<ProdBusiComm> prodBusiCommList = productMapper.fuzzyQueryProduct(name);
            return Result.createSuccessResult(prodBusiCommList.size(),prodBusiCommList);
        }catch (Exception e){
            return Result.createByFailure("出现错误，联系管理员");
        }
    }
    @Autowired
    OneService oneService;
    //商品想详情页
    @Override
    public Result productItem(int userId, int productId) {
        List<Object> list = new ArrayList<Object>();
        try{
           // Product byId = oneService.getById(productId);
           // log.info(byId.toString());
            Product product = productMapper.selectByPrimaryKey(productId);
            //多表联合查询->新建一个类
            System.out.println("111");
            DetailsPageSelect detailsPageSelect = productMapper.detailPage(productId);
            detailsPageSelect.setDetail1(product.getProductImgUrl());

            System.out.println(detailsPageSelect.toString());
            list.add(detailsPageSelect);
            //查询imgList
            List<String> imgUrlList = productMapper.selectImgUrl(productId);
            list.add(imgUrlList);
            //查询userAddress
            List<UserAddress> addressList = userAddressMapper.selectByUserId(userId);
            list.add(addressList);
            return Result.createSuccessResult(list);
        }catch (Exception e){
            return Result.createByFailure("程序出错，联系管理员");
        }

    }

//    @Override
//    public Result getImgListById(int productId) {
//        List<String> list=  productMapper.selectImgUrl(productId);
//        return Result.createSuccessResult(list.size(),list);
//    }
}
