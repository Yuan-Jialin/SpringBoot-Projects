package com.yjl.model.user.dtos;

import lombok.Data;

/**
 * @Author: DLMU 袁佳林
 * @Date: 2024/5/14 13:12
 * @Description:
 **/
@Data
public class LoginDto {
    private String phone;
    private String password;
}
