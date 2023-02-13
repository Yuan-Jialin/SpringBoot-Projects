package com.lanqiao.jd.dao;

import com.lanqiao.jd.entity.ProdBusiComm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestProductMapper {
    @Autowired
    ProductMapper productMapper;

    @Test
    void TestfuzzyQueryProduct(){
        List<ProdBusiComm> v = productMapper.fuzzyQueryProduct("aaa");
        for (ProdBusiComm prodBusiComm : v) {
            System.out.println(prodBusiComm.getProductName());
        }
    }

}
