package com.sky.service;

import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;

public interface UserService {
    User wxLogin(UserLoginDTO userLoginDTO);
}
