package com.heaiai.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heaiai.reggie.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description 菜品mapper
 * @author: Heaiai
 * @create: 2023-03-30 22:51:22
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
