package com.example.reggie.common;

/**
 * @Author 袁佳林
 * @Description 基于ThreadLocal封装工具类，用于保存和获取当前登录用户的id
 * @Date 2023/3/28 14:27
 */
public class BaseContext {

    private static ThreadLocal<Long>threadLocal=new ThreadLocal<>();

    public static void setCurrentId(long id){
        threadLocal.set(id);
    }
    public  static long getCurrentId(){
        return threadLocal.get();
    }

}
