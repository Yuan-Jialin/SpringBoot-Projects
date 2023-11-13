package com.yjl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: DLMU 袁佳林
 * @Date: 2023/11/13 09:33
 * @Description:
 **/

public class JwtTest {
    @Test
    public void TestGen(){
        Map<String,Object> claims=new HashMap<>();
        claims.put("id",1);
        claims.put("username","张三");
        JWT.create().withClaim("user",claims).withExpiresAt(new Date(System.currentTimeMillis()+1000*60*30)).sign(Algorithm.HMAC256("袁佳林万岁"));
    }
}
