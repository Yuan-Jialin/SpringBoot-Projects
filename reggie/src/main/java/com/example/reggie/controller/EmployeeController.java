package com.example.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.reggie.common.R;
import com.example.reggie.entity.Employee;
import com.example.reggie.service.EmployeeService;
import com.example.reggie.service.impl.EmployeeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @Author 袁佳林
 * @Description
 * @Date 2023/3/6 20:13
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    /*
    * 员工登录
    * */
    @PostMapping("/login")
    public R<Employee> login(@RequestBody Employee employee, HttpServletRequest request){

        //1.获取密码并进行md5加密处理
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //根据用户名查数据库
        LambdaQueryWrapper<Employee> employeeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        employeeLambdaQueryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee one = employeeService.getOne(employeeLambdaQueryWrapper);


        //如果没有查到返回登录失败
        if(one==null)
            return R.error("用户名不存在");

        //比对密码
        if(!one.getPassword().equals(password))
            return R.error("密码错误");

        //密码比对成功，查看员工状态(0表示禁用，1表示可用)
        if(one.getStatus()==0)
            return R.error("账号已经禁用");

        //将员工id放入session 方便之后的页面通过id获取数据
        request.getSession().setAttribute("employee",one.getId());

        return R.success(one);
    }

    /*
    * 员工退出登录
    *
    * */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        //清理session中放的员工id
        request.getSession().removeAttribute("employee");
         return R.success("退出成功");
    }
    @PostMapping
    public R<String> save(@RequestBody Employee employee,HttpServletRequest request){
        log.info("新增员工，员工信息为：{}",employee.toString());
        //默认密码为123456
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        long id = (long)request.getSession().getAttribute("employee");
        employee.setUpdateUser(id);
        employee.setCreateUser(id);
        employeeService.save(employee);

        return R.success("新增成功");

    }
}
