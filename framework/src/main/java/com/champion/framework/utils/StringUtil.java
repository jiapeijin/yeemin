/**
 * @Description: 
 * @framework.com.champion.utils
 * @FileName:StringUtil.java
 * @Author: William
 * @CreateTime: 2017-06-06 01:39:24
 */
package com.champion.framework.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @Description: 
 * @ClassName: StringUtil
 * @Auther: William
 * @CreateTime: 2017-06-06 01:39:24
 */
public class StringUtil {

	/**
	 * 
	 * @Description: 判断字符串是否为空
	 * @Param: @param input
	 * @Param: @return
	 * @ReturnType boolean
	 * @Author: William
	 * @CreateTime: 2017-06-06 01:40:50
	 */
	public static boolean isEmpty(String input) {
		if (input == null) {
			return true;
		}
		return StringUtils.isEmpty(input);
	}
	
	/**
	 * 
	 * @Description: 判断字符串是否不为空
	 * @Param: @param input
	 * @Param: @return
	 * @ReturnType boolean
	 * @Author: William
	 * @CreateTime: 2017-06-06 01:40:50
	 */
	public static boolean isNotEmpty(String input) {
		return !isEmpty(input);
	}
}
