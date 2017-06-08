package com.champion.framework.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.util.Collection;
import java.util.Map;

/**
 * @Description
 * @Author william[yeemin_shon@163.com]
 * @Date 2017/5/9 20:53
 */
public final class CollectionUtil {

    /**
     * @Description 判断Collection是否为空
     * @param  * @param collection
     * @return boolean
     * @Author william[yeemin_shon@163.com]
     * @Date 2017/5/9 20:55
     */
    public static boolean isEmpty(Collection<?> collection) {
        return CollectionUtils.isEmpty(collection);
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return MapUtils.isEmpty(map);
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }
}
