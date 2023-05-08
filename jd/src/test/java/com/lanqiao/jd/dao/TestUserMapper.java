package com.lanqiao.jd.dao;

import com.lanqiao.jd.Jd1Application;
import com.lanqiao.jd.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest(classes = Jd1Application.class)
public class TestUserMapper {

    /*int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //ADD
    User selectUserByUserNameAndPassword(User record);

    User selectByUserName(User user);

    User selectByPhoneNumber(String phoneNumber);*/
    @Autowired
    private UserMapper userMapper;
    @Test
    void TestdeleteByPrimaryKey(){
        System.out.println(userMapper.deleteByPrimaryKey(1));
    }
    @Test
    void Testinsert (){
        int yjl = userMapper.insert(new User("袁佳林", "123", new BigDecimal(23.6), "13308565099"));
        System.out.println("-----------");
        System.out.println(yjl);

    }

    @Test
    void TestinsertSelective(){
        userMapper.insertSelective(new User("袁佳林","555"));
    }

    @Test
    void TestselectByPrimaryKey(){
        System.out.println(userMapper.selectByPrimaryKey(3).toString());
    }


}
