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

	/**
	 * 将数组变换成字符串,使用","分割
	 */
	public static String arr2Str(Object[] value) {
		String temp = "";
		if(ValidateUtil.isValid(value)){
			for(Object s:value){
				temp = temp + s + ",";
			}
			return temp.substring(0,temp.length()-1);
		}
		return temp;
	}
	
	public static String arr2Str(Integer[] value) {
		String temp = "";
		if(ValidateUtil.isValid(value)){
			for(Integer s:value){
				temp = temp + s + ",";
			}
			return temp.substring(0,temp.length()-1);
		}
		return temp;
	}
	
	/**
	 * 获取字符串的描述信息
	 */
	public static String getDescString(String str){
		if(str != null && str.trim().length() > 30){
			return str.substring(0,30);
		}
		return str;
	}
}
