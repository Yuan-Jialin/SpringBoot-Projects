package com.lanqiao.jd.service;

import com.lanqiao.jd.service.impl.CartItemServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestCartItemServiceImpl {
@Autowired
CartItemServiceImpl cartItemService;

    @Test
    void TestshowCartItem(){
        cartItemService.showCartItem(2);
    }

}
