package com.yjl.service.impl;

import com.yjl.mapper.UserMapper;
import com.yjl.pojo.User;
import com.yjl.service.UserService;
import com.yjl.utils.Md5Util;
import com.yjl.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

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

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer)map.get("id");
        userMapper.updateAvatar(avatarUrl,id);
    }

    @Override
    public void updatePwd(String newPwd) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer)map.get("id");
        userMapper.updatePwd(Md5Util.getMD5String(newPwd),id);


    }
}
