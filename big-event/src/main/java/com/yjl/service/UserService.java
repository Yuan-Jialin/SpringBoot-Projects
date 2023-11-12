package com.yjl.service;

import com.yjl.pojo.User;
import org.springframework.stereotype.Service;


public interface UserService {

    User findUserByName(String username);
    void register(String username,String password);
}
