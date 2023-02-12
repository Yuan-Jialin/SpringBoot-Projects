package com.lanqiao.jd.dao;

import com.lanqiao.jd.entity.User;
import com.lanqiao.jd.entity.UserAddress;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestUserAddressMapper {

    /*int deleteByPrimaryKey(Integer userAddressId);

    int insert(UserAddress record);

    int insertSelective(UserAddress record);

    UserAddress selectByPrimaryKey(Integer userAddressId);

    int updateByPrimaryKeySelective(UserAddress record);

    int updateByPrimaryKey(UserAddress record);

    List<UserAddress> selectByUserId(Integer userId);*/
    @Autowired
   private UserAddressMapper userAddressMapper;

    @Test
    void TestInsert(){
        userAddressMapper.insert(new UserAddress("北京","13017009511","hh",2) );
    }

    @Test
    void TestselectByUserId(){
        System.out.println(userAddressMapper.selectByUserId(2).toString());
    }



}
