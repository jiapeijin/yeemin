package com.champion.framework.cache;

/**
 * @Description:
 * @Auther： william
 * @Date：2017/6/7 0007 11:17
 */
public interface ICache {

    /**
     * @Description write key-value into cache
     * @MethodName put
     * @param key
     * @param value
     * @return java.lang.Object
     * @author william [yeemin_shon@163.com]
     * @Date 2017/6/7 0007 11:24
     */
    Object put(String key, Object value);

    /**
     * @Description get cache by name from cache
     * @MethodName get
     * @param key
     * @return java.lang.Object
     * @author william [yeemin_shon@163.com]
     * @Date 2017/6/7 0007 11:27
     */
    Object get(String key);

    /**
     * @Description remove key-value from cache
     * @MethodName remove
     * @param key
     * @return void
     * @author william [yeemin_shon@163.com]
     * @Date 2017/6/7 0007 11:28
     */
    void remove(String key);

    /**
     * @Description update key-value from cache
     * @MethodName update
     * @param key
     * @param value
     * @return java.lang.Object
     * @author william [yeemin_shon@163.com]
     * @Date 2017/6/7 0007 11:29
     */
    Object update(String key, Object value);

}
