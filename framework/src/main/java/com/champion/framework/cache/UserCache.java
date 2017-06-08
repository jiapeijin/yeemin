package com.champion.framework.cache;

import com.champion.framework.constant.Global;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description:
 * @Auther： william
 * @Date：2017/6/7 0007 13:42
 */
public class UserCache implements ICache{

    private final static Logger logger = LoggerFactory.getLogger(UserCache.class);

    private static UserCache instance = null;

    private UserCache() {}

    public static UserCache getInstance() {
        if (instance == null) {
            instance = new UserCache();
        }
        return instance;
    }

    /**
     * @Description write key-value into syscache
     * @MethodName put
     * @param key
     * @param value
     * @return java.lang.Object
     * @author william [yeemin_shon@163.com]
     * @Date 2017/6/7 0007 11:24
     */
    @Override
    public Object put(String key, Object value) {
        CacheBase.put(Global.LocaleConfig.USER_CACHE, key, value);
        return value;
    }

    /**
     * @Description get cache by name from syscache
     * @MethodName get
     * @param key
     * @return java.lang.Object
     * @author william [yeemin_shon@163.com]
     * @Date 2017/6/7 0007 11:27
     */
    @Override
    public Object get(String key) {
        return CacheBase.get(Global.LocaleConfig.USER_CACHE, key);
    }

    /**
     * @Description remove key-value from cache
     * @MethodName remove
     * @param key
     * @return void
     * @author william [yeemin_shon@163.com]
     * @Date 2017/6/7 0007 11:28
     */
    @Override
    public void remove(String key) {
        CacheBase.remove(Global.LocaleConfig.USER_CACHE, key);
    }

    /**
     * @Description update key-value from syscache
     * @MethodName update
     * @param key
     * @param value
     * @return java.lang.Object
     * @author william [yeemin_shon@163.com]
     * @Date 2017/6/7 0007 11:29
     */
    @Override
    public Object update(String key, Object value) {
        remove(key);
        put(key, value);
        return value;
    }
}
