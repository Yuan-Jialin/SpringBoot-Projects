package com.yjl.controller;

import com.yjl.pojo.Result;
import com.yjl.pojo.User;
import com.yjl.service.UserService;


import com.yjl.utils.JwtUtil;
import com.yjl.utils.Md5Util;
import com.yjl.utils.ThreadLocalUtil;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.constraints.Pattern;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
            userService.register(userName, Md5Util.getMD5String(passWord));
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
            Map<String,Object>claims=new HashMap<>();
            claims.put("id",user.getId());
            claims.put("username",userName);
            String string = JwtUtil.genToken(claims);
            return Result.success(string);
        }
        return Result.error("密码错误");
    }
    @GetMapping("/userInfo")
    public Result<User>userInfo(){

        //System.out.println(userName);
        Map<String,Object> o = ThreadLocalUtil.get();
        String o1 = (String) o.get("username");
        User user = userService.findUserByName(o1);
        return Result.success(user);
    }
    @PutMapping(value="/update")
    public Result update(@RequestBody User user){//@requestBody 将前端传过来的json封装进一个对象
        userService.update(user);
        return Result.success();
    }
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody  Map<String,String> params){
        String oldPWd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        if(!StringUtils.hasLength(oldPWd)||!StringUtils.hasLength(newPwd)||!StringUtils.hasLength(rePwd)){
            return Result.error("缺少必要的参数");
        }
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String)map.get("username");
        User originUser = userService.findUserByName(username);
        if(!originUser.getPassword().equals(Md5Util.getMD5String(oldPWd))){
            return Result.error("原密码不正确");
        }
        if(!newPwd.equals(rePwd)){
            return Result.error("两次填写的新密码不同");
        }
        userService.updatePwd(newPwd);
        return Result.success();
    }
}
