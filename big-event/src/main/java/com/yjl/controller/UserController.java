package com.yjl.controller;

import com.yjl.pojo.Result;
import com.yjl.pojo.User;
import com.yjl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: DLMU 袁佳林
 * @Date: 2023/11/11 21:53
 * @Description:
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public Result register(String userName,String  passWord){
        User user=userService.findUserByName(userName);
        if(user==null){
            userService.register(userName,passWord);
            return Result.success();
        }else{
            return Result.error("用户名已经被使用");
        }

    }
}
