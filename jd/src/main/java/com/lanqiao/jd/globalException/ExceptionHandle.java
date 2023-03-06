package com.lanqiao.jd.globalException;

import com.lanqiao.jd.util.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class ExceptionHandle {

    /**
     *  @ExceptionHandler(value = Exception.class):声明要捕获的异常类
     * @return
     */
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public Result handle(RuntimeException e){
        return Result.createByFailure(e.getMessage());
    }
}
