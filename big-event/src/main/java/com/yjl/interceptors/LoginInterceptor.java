package com.yjl.interceptors;

import com.yjl.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * @Author: DLMU 袁佳林
 * @Date: 2023/11/14 13:57
 * @Description:
 **/
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //令牌验证
        String token = request.getHeader("Authorization");
        try {
            Map<String,Object>cliams= JwtUtil.parseToken(token);
            //放行
            return true;
        }catch (Exception e){
            response.setStatus(401);
            //不放行
            return false;
        }

    }
}
