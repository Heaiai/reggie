package com.heaiai.reggie.controller;

import com.heaiai.reggie.service.DishFlavorService;
import com.heaiai.reggie.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description 菜品管理
 * @author: Heaiai
 * @create: 2023-09-02 21:55:20
 */
@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishService dishService;
    @Autowired
    private DishFlavorService dishFlavorService;
}
