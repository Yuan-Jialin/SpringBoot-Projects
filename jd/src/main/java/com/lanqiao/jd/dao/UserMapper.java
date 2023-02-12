package com.lanqiao.jd.dao;

import com.lanqiao.jd.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //ADD
    User selectUserByUserNameAndPassword(User record);

    User selectByUserName(User user);

    User selectByPhoneNumber(String phoneNumber);
}