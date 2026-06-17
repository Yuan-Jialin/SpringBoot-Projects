package com.heima.user.controller.v1;

import com.heima.user.service.ApUserService;
import com.yjl.model.common.dtos.ResponseResult;
import com.yjl.model.user.dtos.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: DLMU 袁佳林
 * @Date: 2024/5/14 13:10
 * @Description:
 **/
@RestController
@RequestMapping("/api/v1/login")
public class ApUserLoginController {
    @Autowired
    private ApUserService apUserService;

    @PostMapping("/login_auth")
    public ResponseResult login(@RequestBody LoginDto dto) {
        return apUserService.login(dto);
    }
}
