package com.champion.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Description 反射工具类
 * @Author william[yeemin_shon@163.com]
 * @Date 2017/5/10 0010 15:52
 */
public class ReflectionUtil {

    private static final Logger logger = LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     * @Description 创建实例
     * @param  * @param cls
     * @return java.lang.Object
     * @Author william[yeemin_shon@163.com]
     * @Date 2017/5/10 0010 15:45
     */
    public static Object newInstance(Class<?> cls) {
        Object instance;
        try {
            instance = cls.newInstance();
        } catch (Exception e) {
            logger.error("new instance failure", e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * @Description 调用方法
     * @param  * @param obj
     * @param method
     * @param args
     * @return java.lang.Object
     * @Author william[yeemin_shon@163.com]
     * @Date 2017/5/10 0010 15:48
     */
    public static Object invokeMethod(Object obj, Method method, Object... args) {
        Object result;
        try {
            method.setAccessible(true);
            result = method.invoke(obj, args);
        } catch (Exception e) {
            logger.error("invoke method failure", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * @Description 设置成员变量的值
     * @param  * @param obj
     * @param field
     * @param value
     * @return void
     * @Author william[yeemin_shon@163.com]
     * @Date 2017/5/10 0010 15:50
     */
    public static void setField(Object obj, Field field, Object value) {
        try {
            field.setAccessible(true);
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            logger.error("set field failure", e);
            throw new RuntimeException(e);
        }
    }
}
