/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.champion.framework.utils;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

/**
 * 封装各种生成唯一性ID算法的工具类.
 * @author ThinkGem
 * @version 2013-01-15
 */
@Service
@Lazy(false)
public class IdGen  {

	private static SecureRandom random = new SecureRandom();
	
	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * 
	 * @描述：生成19位UUID
	 * @参数名称：@return
	 * @返回类型 String
	 * @作者 生一鸣
	 * @创建时间 2017 2017年3月1日下午3:48:09
	 */
	public static String shortUUid() {
		return UUIDUtils.uuid();
	}

	/**
	 * @Description 生成long类型的id
	 * @MethodName longId
	 * @return java.lang.Long
	 * @author william [yeemin_shon@163.com]
	 * @Date 2017/6/6 0006 14:39
	 */
	public static Long longId() {
	    Long t = System.nanoTime();
	    Random random = new Random();
	    return t * 1000 + (long) random.nextInt(1000);
    }

	public static void main(String[] args) {

	}

}
