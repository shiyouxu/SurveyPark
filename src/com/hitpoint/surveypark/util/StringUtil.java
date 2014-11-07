package com.hitpoint.surveypark.util;
/**
 * 字符串工具类
 * @author leo.zhang
 */
public class StringUtil {
	/**
	 * 将字符串数组按照tag分割
	 */
	public static String[] str2Arr(String str,String tag){
		if(ValidateUtil.isValid(str)){
			return str.split(tag);
		}
		return null;
	}

	/**
	 * 判断values数组中是否含有value
	 */
	public static boolean contains(String[] values, String value) {
		if(ValidateUtil.isValid(values)){
			for (String s : values) {
				if(s.equals(value)){
					return true;
				}
			}
		}
		return false;
	}
}
