package com.champion.framework.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * @Description: cache base
 * @Auther： william
 * @Date：2017/6/7 0007 10:59
 */
public class CacheBase {

//    private final static Logger logger = LoggerFactory.getLogger(CacheBase.class);

    private static CacheManager cacheManager;

    /**
     * @Description get cache by key
     * @MethodName get
     * @param cacheName
     * @param key
     * @return java.lang.Object
     * @author william [yeemin_shon@163.com]
     * @Date 2017/6/7 0007 11:13
     */
    public static Object get(String cacheName, String key) {
        Element element = getCache(cacheName).get(key);
        return element == null ? null : element.getObjectValue();
    }

    /**
     * @Description get cache by key
     *              if the cache is null, return defaultValue
     * @MethodName get
     * @param cacheName
     * @param key
     * @param defaultValue
     * @return java.lang.Object
     * @author william [yeemin_shon@163.com]
     * @Date 2017/6/7 0007 11:15
     */
    public static Object get(String cacheName, String key, Object defaultValue) {
        Object obj = get(cacheName, key);
        return obj == null ? defaultValue : obj;
    }

    /**
     * @Description write key-value into cache by cache name
     * @MethodName put
     * @param cacheName
     * @param key
     * @param value
     * @return void
     * @author william [yeemin_shon@163.com]
     * @Date 2017/6/7 0007 11:08
     */
    public static void put(String cacheName, String key, Object value) {
        Element element = new Element(key, value);
        getCache(cacheName).put(element);
    }

    /**
     * @Description remove cache by key and cache name
     * @MethodName remove
     * @param cacheName
     * @param key
     * @return void
     * @author william [yeemin_shon@163.com]
     * @Date 2017/6/7 0007 11:10
     */
    public static void remove(String cacheName, String key) {
        getCache(cacheName).remove(key);
    }

    /**
     * @Description get cache by name;
     *              if the cache isn't exit, create one
     * @MethodName getCache
     * @param cacheName
     * @return net.sf.ehcache.Cache
     * @author william [yeemin_shon@163.com]
     * @Date 2017/6/7 0007 11:06
     */
    private static Cache getCache(String cacheName){
        cacheManager = getCacheManager();
        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null){
//            logger.info("can not find cache by name: " + cacheName + "; creating cache by name: " + cacheName);
            cacheManager.addCache(cacheName);
            cache = cacheManager.getCache(cacheName);
            cache.getCacheConfiguration().setEternal(true);
        }
        return cache;
    }

    public static CacheManager getCacheManager() {
        return cacheManager;
    }

    public static void setCacheManager(CacheManager cacheManager) {
        CacheBase.cacheManager = cacheManager;
    }
}
