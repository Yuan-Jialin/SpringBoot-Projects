package com.lanqiao.jd.dao;

import com.lanqiao.jd.entity.Cart_Product_Business;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestcartItemMapper {
    @Autowired
    private CartItemMapper cartItemMapper;
    @Test
    void TestselectCartItemByuserId(){
        List<Cart_Product_Business> cartProductBusinesses = cartItemMapper.selectCartItemByuserId(2);
        System.out.println(cartProductBusinesses);
    }

}
