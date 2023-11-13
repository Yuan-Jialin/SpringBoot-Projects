package com.yjl.controller;

import com.yjl.pojo.Result;
import com.yjl.pojo.User;
import com.yjl.service.UserService;


import com.yjl.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: DLMU 袁佳林
 * @Date: 2023/11/11 21:53
 * @Description:
 **/
@RestController
@Validated
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$")String userName, @Pattern(regexp = "^\\S{5,16}$")String passWord) {

        User user = userService.findUserByName(userName);
        if (user == null) {
            userService.register(userName, passWord);
            return Result.success();
        } else {
            return Result.error("用户名已经被使用");
        }
    }
    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{5,16}$")String userName, @Pattern(regexp = "^\\S{5,16}$")String passWord){
        User user = userService.findUserByName(userName);
        if(user==null){
            return Result.error("用户名不存在");
        }
        if(Md5Util.getMD5String(passWord).equals(user.getPassword())){
            return Result.success("jwt token令牌..");
        }
        return Result.error("密码错误");
    }
}
