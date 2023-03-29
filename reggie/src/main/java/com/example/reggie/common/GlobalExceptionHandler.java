package com.example.reggie.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @Author 袁佳林
 * @Description
 * @Date 2023/3/8 11:20
 */

//代理加了RestController注解的controller
@ControllerAdvice(annotations = {RestController.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    //对SQLIntegrityConstraintViolationException异常进行处理
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException ex) {
        String message = ex.getMessage();
        log.error(message);
        if(message.contains("Duplicate entry")){
            String[] s = message.split(" ");
            String msg=s[2]+"已存在";
            return R.error(msg);

        }

        return R.error("未知错误");




    }

    @ExceptionHandler(CustomException.class)
    public R<String> exceptionHandler(CustomException ex) {
        String message = ex.getMessage();
        log.error(message);
        return R.error(message);
    }
}
