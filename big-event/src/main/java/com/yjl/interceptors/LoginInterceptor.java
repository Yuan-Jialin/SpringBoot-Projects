package com.yjl.interceptors;

import com.yjl.pojo.Result;
import com.yjl.pojo.User;
import com.yjl.utils.JwtUtil;
import com.yjl.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
            Map<String,Object>claims= JwtUtil.parseToken(token);
            //System.out.println(claims.get("username"));
            ThreadLocalUtil.set(claims);
            //放行
            return true;
        }catch (Exception e){
            response.setStatus(401);
            //不放行
            return false;
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //防止内存泄露
        ThreadLocalUtil.remove();
    }


}
