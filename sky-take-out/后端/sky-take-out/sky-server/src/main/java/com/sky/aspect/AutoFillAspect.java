package com.sky.aspect;

import com.sky.annotation.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * @Author: DLMU 袁佳林
 * @Date: 2023/12/14 09:27
 * @Description:
 **/
/*
自定义切面，实现公共字段自动填充处理逻辑
 */
@Aspect
@Component
@Slf4j
public class AutoFillAspect {
    /*
     切入点
     */
    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
    public void autoFIllPointCut(){

    }
    /*
    前置通知，在通知中进行公共字段赋值
     */
    @Before("autoFIllPointCut()")
    public void autoFill(JoinPoint joinPoint)  {
        log.info("开始进行公共字段自动填充..");

        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        AutoFill annotation = signature.getMethod().getAnnotation(AutoFill.class);
        OperationType value = annotation.value();


        Object[] args = joinPoint.getArgs();
        if(args==null||args.length==0){
            return;
        }
        Object arg = args[0];

        LocalDateTime now = LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId();

        if(value==OperationType.INSERT){
            try {
                Method setCreateTime = arg.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setCreateUser = arg.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                Method setUpdateTime = arg.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = arg.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                setCreateTime.invoke(arg,now);
                setUpdateTime.invoke(arg,now);
                setUpdateUser.invoke(arg,currentId);
                setCreateUser.invoke(arg,currentId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }else if(value==OperationType.UPDATE){

            try {
                Method setUpdateTime = arg.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = arg.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                setUpdateTime.invoke(arg,now);
                setUpdateUser.invoke(arg,currentId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }
}
