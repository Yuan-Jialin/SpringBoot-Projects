package com.yjl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yjl.utils.JwtUtil;
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
    public void TestGen() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "1");
        String sign = JWT.create().withClaim("user", claims).withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 30)).sign(Algorithm.HMAC256("袁佳林万岁"));
        System.out.println(sign);
    }

    @Test
    public void xiu(){
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 3);
        claims.put("username", "jahup");
        String string = JwtUtil.genToken(claims);
        System.out.println(string);
    }

    @Test
    void parseToken() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6IuW8oOS4iSJ9LCJleHAiOjE2OTk5NDE1Nzd9.Hi3sHxmA7_mOMMipA0Ms500ZhtnxlzhV1lRborRIl14";
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("袁佳林万岁")).build();
        DecodedJWT verify = jwtVerifier.verify(token);
        Map  <String, Claim>claims=verify.getClaims();
        System.out.println(claims.get("user"));

    }

}
