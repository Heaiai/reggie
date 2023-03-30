package com.heaiai.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heaiai.reggie.common.R;
import com.heaiai.reggie.entity.Category;
import com.heaiai.reggie.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description
 * @author: Heaiai
 * @create: 2023-03-30 22:52:31
 */
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    /***
     * @Description:分页查询方法
     * @Author:Heaiai
     * @Create:2023/3/31 0:24
     */
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize){
        Page<Category> pageinfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Category::getSort);
        categoryService.page(pageinfo,queryWrapper);
        return R.success(pageinfo);
    }
    /***
     * @Description:保存方法
     * @Author:Heaiai
     * @Create:2023/3/31 0:25
     */
    @PostMapping
    public R<String> save(@RequestBody Category category){
        categoryService.save(category);
        return R.success();
    }
    /***
     * @Description:删除方法
     * @Author:Heaiai
     * @Create:2023/3/31 0:25
     */
    @DeleteMapping
    public R<String> delete(Long ids){
        categoryService.removeById(ids);
        return R.success("删除成功");
    }
    /***
     * @Description:更新方法
     * @Author:Heaiai
     * @Create:2023/3/31 0:25
     */
    @PutMapping
    public R<String> update(@RequestBody Category category){
        log.info("修改分类信息：{}",category);
        categoryService.updateById(category);
        return R.success("修改完成");
    }
}
