package com.lanqiao.jd.service;

import com.lanqiao.jd.entity.User;

/**
 * @author 刘佳昇
 * @Date 2019/8/14 15:23
 */

public interface TokenService {
    String getToken(User user);

    String getMd5(String str);
}
