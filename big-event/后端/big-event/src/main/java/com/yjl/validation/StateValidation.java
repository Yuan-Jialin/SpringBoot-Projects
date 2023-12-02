package com.yjl.validation;

import com.yjl.anno.State;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @Author: DLMU 袁佳林
 * @Date: 2023/11/17 21:07
 * @Description:
 **/

public class StateValidation implements ConstraintValidator<State,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(value==null)
        return false;
        if(value.equals("已发布")||value.equals("草稿"))
            return true;
        return false;
    }
}
