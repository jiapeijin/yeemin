package com.champion.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Description 读取配置文件内容
 * @Author william[yeemin_shon@163.com]
 * @Date 2017/5/9 0009 17:48
 */
public final class PropsUtil {

    private static final Logger logger = LoggerFactory.getLogger(PropsUtil.class);

    /**
     * @Description 加载属性文件
     * @param  * @param fileName
     * @return java.util.Properties
     * @Author william[yeemin_shon@163.com]
     * @Date 2017/5/9 0009 17:12
     */
    public static Properties loadProps(String fileName) {


        Properties props = null;
        InputStream is = null;
        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if (is == null) {
                throw new FileNotFoundException(fileName + " file is not found");
            }
            props = new Properties();
            props.load(is);
        }catch (IOException e) {
            logger.error("load properties file failure", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error("close input stream failure", e);
                }
            }
        }
        return props;
    }

    /**
     * @Description 获取字符型属性（默认为空字符串）
     * @param  * @param props
     * @param key
     * @return java.lang.String
     * @Author william[yeemin_shon@163.com]
     * @Date 2017/5/9 0009 17:20
     */
    public static String getString(Properties props, String key) {
        return getString(props, key, "");
    }

    /**
     * @Description 获取字符型属性（可指定默认值）
     * @param  * @param props
     * @param key
     * @param defaultValue
     * @return java.lang.String
     * @Author william[yeemin_shon@163.com]
     * @Date 2017/5/9 0009 17:19
     */
    public static String getString(Properties props ,String key, String defaultValue) {
        String value = defaultValue;
        if (props.containsKey(key)) {
            value = props.getProperty(key);
        }
        return value;
    }

    /**
     * @Description 获取数值型属性值（默认0）
     * @param  * @param props
     * @param key
     * @return int
     * @Author william[yeemin_shon@163.com]
     * @Date 2017/5/9 0009 17:39
     */
    public static int getInt(Properties props, String key) {
        return getInt(props, key, 0);
    }

    /**
     * @Description 获取数值型属性值（可指定默认值）
     * @param  * @param props
     * @param key
     * @param defaultValue
     * @return int
     * @Author william[yeemin_shon@163.com]
     * @Date 2017/5/9 0009 17:38
     */
    public static int getInt(Properties props, String key, int defaultValue) {
        int value = defaultValue;
        if (props.containsKey(key)) {
            value = CastUtil.castInt(props.getProperty(key));
        }
        return value;
    }

    /**
     * @Description 获取boolean型属性（默认为false）
     * @param  * @param props
     * @param key
     * @return boolean
     * @Author william[yeemin_shon@163.com]
     * @Date 2017/5/9 0009 17:47
     */
    public static boolean getBoolean(Properties props, String key) {
        return getBoolean(props, key, false);
    }

    /**
     * @Description 获取boolean型属性（可指定默认值）
     * @param  * @param props
     * @param key
     * @param defalutValue
     * @return boolean
     * @Author william[yeemin_shon@163.com]
     * @Date 2017/5/9 0009 17:46
     */
    public static boolean getBoolean(Properties props, String key, Boolean defalutValue) {
        boolean value = defalutValue;
        if (props.containsKey(key)) {
            value = CastUtil.castBoolean(props.getProperty(key));
        }
        return value;
    }
}