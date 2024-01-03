package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.User;
import com.sky.enumeration.OperationType;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @Author: DLMU 袁佳林
 * @Date: 2023/12/20 19:23
 * @Description:
 **/
@Mapper
public interface UserMapper {
    @Select("select * from user where openid=#{openid}")
    User getByOpenid(String openid);

    void insert(User user);

    @Select("select * from user where id = #{id}")
    User getById(Long userId);

    Integer countByMap(Map map);

}
