package com.heaiai.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heaiai.reggie.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description 菜品口味关系表
 * @author: Heaiai
 * @create: 2023-09-02 21:51:42
 */
@Mapper
public interface DishFlavorMapper extends BaseMapper<DishFlavor> {
}
