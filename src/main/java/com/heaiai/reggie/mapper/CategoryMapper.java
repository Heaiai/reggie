package com.heaiai.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heaiai.reggie.entity.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description 产品分类mapper
 * @author: Heaiai
 * @create: 2023-03-30 22:51:22
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
