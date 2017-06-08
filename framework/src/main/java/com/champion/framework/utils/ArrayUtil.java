package com.champion.framework.utils;

import org.apache.commons.lang3.ArrayUtils;

/**
 * @Description 数组工具类
 * @Author william[yeemin_shon@163.com]
 * @Date 2017/5/10 0010 16:05
 */
public final class ArrayUtil {

    /**
     * @Description 判断数组是否非空
     * @param  * @param array
     * @return boolean
     * @Author william[yeemin_shon@163.com]
     * @Date 2017/5/10 0010 16:06
     */
    public static boolean isNotEmpty(Object[] array) {
        return !ArrayUtils.isEmpty(array);
    }

    /**
     * @Description 判断数组是否为空
     * @param  * @param array
     * @return boolean
     * @Author william[yeemin_shon@163.com]
     * @Date 2017/5/10 0010 16:06
     */
    public static boolean isEmpty(Object[] array) {
        return ArrayUtils.isEmpty(array);
    }

}
