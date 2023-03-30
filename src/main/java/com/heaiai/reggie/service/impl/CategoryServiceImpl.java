package com.heaiai.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heaiai.reggie.common.CustomeException;
import com.heaiai.reggie.entity.Dish;
import com.heaiai.reggie.entity.Setmeal;
import com.heaiai.reggie.mapper.CategoryMapper;
import com.heaiai.reggie.entity.Category;
import com.heaiai.reggie.service.CategoryService;
import com.heaiai.reggie.service.DishService;
import com.heaiai.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @description 产品分类实现类
 * @author: Heaiai
 * @create: 2023-03-30 22:53:52
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;
    @Override
    public boolean removeById(Serializable id) {
        //根据分类Id查询是否有关联菜品
        LambdaQueryWrapper<Dish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Dish::getCategoryId,id);
        long count1 =  dishService.count(lambdaQueryWrapper);
        if(count1 > 1){
            throw new CustomeException("当前分类已关联菜品，不能删除");
        }
        //根据分类id查询是否有关联套餐
        LambdaQueryWrapper<Setmeal> mealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        mealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        long count2 = setmealService.count(mealLambdaQueryWrapper);
        if(count2 > 1){
            throw  new CustomeException("当前分类已关联套餐，不能删除");
        }
        super.removeById(id);
        return true;
    }
}
