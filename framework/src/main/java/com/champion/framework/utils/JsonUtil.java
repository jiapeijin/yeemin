package com.champion.framework.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description json工具类
 * @Author william[yeemin_shon@163.com]
 * @Date 2017/5/10 0010 17:03
 */
public final class JsonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * @Description 将POJO转为JSON
     * @param  * @param obj
     * @return java.lang.String
     * @Author william[yeemin_shon@163.com]
     * @Date 2017/5/10 0010 17:05
     */
    public static <T> String toJson(T obj) {
        String json;
        try {
            json = OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("convert POJO to JSON failure", e);
            throw new RuntimeException(e);
        }
        return json;
    }

    /**
     * @Description 将JSON 转为POJO
     * @param  * @param json
     * @param type
     * @return T
     * @Author william[yeemin_shon@163.com]
     * @Date 2017/5/10 0010 17:08
     */
    public static <T> T fromJson(String json, Class<T> type) {
        T pojo;
        try {
            pojo = OBJECT_MAPPER.readValue(json, type);
        } catch (Exception e) {
            logger.error("convert JSON to POJO failure", e);
            throw new RuntimeException(e);
        }
        return pojo;
    }
}
