package com.lanqiao.jd.service;

import com.lanqiao.jd.entity.Product;
import com.lanqiao.jd.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author 袁佳林
 * @Description
 * @Date 2023/2/20 15:35
 */
@SpringBootTest
public class TestProductService {
        @Autowired
        ProductServiceImpl productService;
        //Integer productId, String productName, Integer productPrice, String productImgUrl, Integer stock, Integer businessId
        @Test
        void TestInsert(){
                productService.insertProduct(new Product(5,"汤臣一品",20,"",10,1));
        }
}
