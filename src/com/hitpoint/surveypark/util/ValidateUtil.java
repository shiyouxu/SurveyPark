package com.hitpoint.surveypark.util;

import java.util.Collection;

/**
 * 校验工具类
 * @author leo.zhang
 *
 */
public class ValidateUtil {
	/**
	 * 判断字符串有效性
	 * @param src
	 * @return
	 */
	public static boolean isValid(String src){
		if(src ==null ||"".equals(src.trim())){
			return false;
		}
		return true;
	}
	
	/**
	 * 判断集合的有效性
	 */
	public static boolean isValid(Collection c){
		if(c==null || c.isEmpty()){
			return false;
		}
		return true;
	}
}
