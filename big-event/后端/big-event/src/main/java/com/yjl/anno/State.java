package com.yjl.anno;

import com.yjl.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {StateValidation.class})
public @interface State {
    //提供校验失败后的提示信息
    String message() default "{state参数的值只能是已发布或者草稿}";
    //指定分组
    Class<?>[] groups() default {};
    //获取到State注解的附加信息
    Class<? extends Payload>[] payload() default {};
}
