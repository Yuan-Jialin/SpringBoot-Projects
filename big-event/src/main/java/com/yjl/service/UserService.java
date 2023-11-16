package com.yjl.service;

import com.yjl.pojo.User;
import org.springframework.stereotype.Service;


public interface UserService {

    User findUserByName(String username);
    void register(String username,String password);

    void update(User user);

    void updateAvatar(String avatarUrl);

    void updatePwd(String newPwd);
}
