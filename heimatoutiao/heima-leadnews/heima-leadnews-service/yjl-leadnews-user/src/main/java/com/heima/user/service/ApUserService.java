package com.heima.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yjl.model.common.dtos.ResponseResult;
import com.yjl.model.user.dtos.LoginDto;
import com.yjl.model.user.pojos.ApUser;

public interface ApUserService extends IService<ApUser> {

    public ResponseResult login(LoginDto dto);


}
