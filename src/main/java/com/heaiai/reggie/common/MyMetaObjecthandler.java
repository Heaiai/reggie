package com.heaiai.reggie.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @description 自定义元数据处理器
 * @author: Heaiai
 * @create: 2023-03-05 22:52:47
 */
@Component
@Slf4j
public class MyMetaObjecthandler implements MetaObjectHandler {
    /***
     * @Description: 插入操作，自动填充
     * @Author:Heaiai
     * @Create:2023/3/5 22:54
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("公共字段自动填充[insert]...");
        log.info(metaObject.toString());
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime",LocalDateTime.now());
        metaObject.setValue("createUser",new Long(1));
        metaObject.setValue("updateUser",new Long(1));
    }

    /***
     * @Description: 更新操作，自动填充
     * @Author:Heaiai
     * @Create:2023/3/5 22:56
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("公共字段自动填充[update]...");
        log.info(metaObject.toString());
        metaObject.setValue("updateTime",LocalDateTime.now());
        metaObject.setValue("updateUser",new Long(1));
    }
}