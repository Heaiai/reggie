package com.heaiai.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heaiai.reggie.entity.Category;

/**
 * @description 产品分类服务
 * @author: Heaiai
 * @create: 2023-03-30 22:53:20
 */
public interface CategoryService extends IService<Category> {
    /***
     * @Description:删除分类方法
     * @Author:Heaiai
     * @Create:2023/8/31 21:26
     */
    void remove(Long id);

}
