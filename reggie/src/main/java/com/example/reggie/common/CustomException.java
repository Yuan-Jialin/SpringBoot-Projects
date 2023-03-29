package com.example.reggie.common;

/**
 * @Author 袁佳林
 * @Description 自定义业务异常
 * @Date 2023/3/29 14:46
 */

public class CustomException extends RuntimeException{
    public CustomException(String message){
        super(message);
    }
}
