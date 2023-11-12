package com.yjl.service.impl;

import com.yjl.mapper.UserMapper;
import com.yjl.pojo.User;
import com.yjl.service.UserService;
import com.yjl.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: DLMU 袁佳林
 * @Date: 2023/11/11 21:54
 * @Description:
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findUserByName(String username) {
        return userMapper.findByUserName(username);
    }

    @Override
    public void register(String username, String password) {
        String md5String = Md5Util.getMD5String(password);
        userMapper.add(username,password);
    }
}
