package com.lanqiao.jd.service;

import com.lanqiao.jd.entity.User;

/**
 * @Author 袁佳林
 * @Description
 * @Date 2023/1/7 19:22
 */
public interface TokenService {
    String getToken(User user);

    String getMd5(String str);
}
