package com.heaiai.reggie.common;

/**
 * @description 基于ThreadLocal封装工具类，用于保存和获取当前的用户id
 * @author: Heaiai
 * @create: 2023-03-13 22:40:41
 */
public class BaseContext {

    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    /***
     * @Description: 赋值当前登录用户的id
     * @Author:Heaiai
     * @Create:2023/3/13 22:42
     */
    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    /***
     * @Description:获取当前登录用户的id
     * @Author:Heaiai
     * @Create:2023/3/13 22:42
     */
    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
