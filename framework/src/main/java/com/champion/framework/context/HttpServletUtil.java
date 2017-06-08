package com.champion.framework.context;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Description:
 * @Auther： william
 * @Date：2017/6/7 0007 11:55
 */
public class HttpServletUtil {

    /**
     * @Description get local HttpServletRequest
     * @MethodName getRequest
     * @param
     * @return javax.servlet.http.HttpServletRequest
     * @author william [yeemin_shon@163.com]
     * @Date 2017/6/7 0007 11:57
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
    }

    /**
     * @Description get local HttpServletResponse
     * @MethodName getRequest
     * @param
     * @return javax.servlet.http.HttpServletRequest
     * @author william [yeemin_shon@163.com]
     * @Date 2017/6/7 0007 11:57
     */
    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getResponse();
    }

    /**
     * @Description get local session
     * @MethodName getSession
     * @param
     * @return javax.servlet.http.HttpSession
     * @author william [yeemin_shon@163.com]
     * @Date 2017/6/7 0007 12:33
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }
}
