package com.heaiai.reggie.common;

/**
 * @description 自定义异常
 * @author: Heaiai
 * @create: 2023-03-30 23:50:19
 */
public class CustomeException extends RuntimeException {
    public CustomeException(String message){
        super(message);
    }
}
