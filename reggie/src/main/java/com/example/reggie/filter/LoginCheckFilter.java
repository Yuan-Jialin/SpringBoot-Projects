package com.example.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.example.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author 袁佳林
 * @Description 检查用户是否登录，没有登录就跳转至登录页面
 * @Date 2023/3/7 16:29
 */

//拦截所有请求
@Slf4j
@Component
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    //路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER=new AntPathMatcher();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        //获取url
        String requestURI = request.getRequestURI();
        log.info("拦截到请求：{}",requestURI);


        //判断本次请求是否需要处理
        boolean check = check(requestURI);

        if(check){
            //不需要处理直接放行
            filterChain.doFilter(request,response);
            return;
        }else{
            Object employee = request.getSession().getAttribute("employee");
            if(employee==null){
                response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
                return;
            }else{
                //已经登录，直接放行
                filterChain.doFilter(request,response);
                return;

            }


        }



    }

    public boolean check(String requestURL){
        //定义不需要处理的请求路径
        String []urls=new String[]{
                "/employee/login",
                "/employee/logout",
                "backend/**",
                "/front/**"
        };
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURL);
            if(match==true)
            {
                return true;
            }
        }
        return false;

    }
}
