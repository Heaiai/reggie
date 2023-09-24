package com.heaiai.reggie.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @description 全局异常处理
 * @author: Heaiai
 * @create: 2023-03-05 21:09:55
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException ex){
        log.info(ex.getMessage());
        if(ex.getMessage().contains("Duplicate entry")){
            String[] split = ex.getMessage().split(" ");
            String msg = split[2];
            return R.error(msg+",已存在");
        }
        return R.error("遇到未知错误");
    }
    /***
     * @Description:捕获正常的业务异常信息
     * @Author:Heaiai
     * @Create:2023/8/31 21:32
     */
    @ExceptionHandler(CustomeException.class)
    public R<String> exceptionHandler(CustomeException ex){
        log.info("捕获到的业务异常信息为:{}",ex.getMessage());
        return R.error(ex.getMessage());
    }
}
