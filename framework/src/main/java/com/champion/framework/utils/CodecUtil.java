package com.champion.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @Description 编码与解码操作工具类
 * @Author william[yeemin_shon@163.com]
 * @Date 2017/5/10 0010 16:58
 */
public final class CodecUtil {

    private static final Logger logger = LoggerFactory.getLogger(CodecUtil.class);

    /**
     * @Description 将url编码
     * @param  * @param source
     * @return java.lang.String
     * @Author william[yeemin_shon@163.com]
     * @Date 2017/5/10 0010 17:01
     */
    public static String encodeURL(String source) {
        String target;
        try {
            target = URLEncoder.encode(source, "utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("encode url failure", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * @Description 将url解码
     * @param  * @param source
     * @return java.lang.String
     * @Author william[yeemin_shon@163.com]
     * @Date 2017/5/10 0010 17:02
     */
    public static String decodeURL(String source) {
        String target;
        try {
            target = URLDecoder.decode(source, "utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("decode url failure", e);
            throw new RuntimeException(e);
        }
        return target;
    }

}
