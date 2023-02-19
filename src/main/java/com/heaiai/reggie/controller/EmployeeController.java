package com.heaiai.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.heaiai.reggie.common.R;
import com.heaiai.reggie.entity.Employee;
import com.heaiai.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @description
 * @author: Heaiai
 * @create: 2023-02-15 22:06:30
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    /***
     * @Description: 前台页面登录方法
     * @Author:Heaiai
     * @Create:2023/2/15 22:13
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        //1、判断页面提交的用户名密码不能为空
        if(null == employee || StringUtils.isBlank(employee.getUsername()) || StringUtils.isBlank(employee.getPassword())){
            return R.error("入参不能为空");
        }
        //2、将页面提交的密码进行MD5加密处理
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        // 3、根据页面提交的用户名username查询数据
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);

        //4、如果没有查询到则返回登录失败结果
        if(null == emp){
            return R.error("登录失败");
        }
        //5、密码对比，如果不一致则返回登录失败结果
        if(!password.equals(emp.getPassword())){
            return R.error("登录失败");
        }
        //6、查询员工状态，如果未已禁用，则返回员工已禁用结果
        if(0 == emp.getStatus()){
            return R.error("当前员工状态为禁用状态");
        }
        request.getSession().setAttribute("employee",emp.getId());
        return R.success(emp);
    }

    /***
     * @Description:员工退出登录方法
     * @Author:Heaiai
     * @Create:2023/2/19 21:36
     */
    @RequestMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        request.removeAttribute("employee");
        return R.success("登录成功");
    }
}
