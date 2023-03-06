package com.example.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author 袁佳林
 * @Description
 * @Date 2023/3/6 20:08
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
