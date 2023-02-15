package com.heaiai.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heaiai.reggie.entity.Employee;
import com.heaiai.reggie.mapper.EmployeeMapper;
import com.heaiai.reggie.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @description
 * @author: Heaiai
 * @create: 2023-02-15 22:05:21
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
