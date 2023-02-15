package com.heaiai.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heaiai.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description
 * @author: Heaiai
 * @create: 2023-02-15 22:02:36
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
