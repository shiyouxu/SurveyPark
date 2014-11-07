package com.hitpoint.surveypark.util;
/**
 * �ַ���������
 * @author leo.zhang
 */
public class StringUtil {
	/**
	 * ���ַ������鰴��tag�ָ�
	 */
	public static String[] str2Arr(String str,String tag){
		if(ValidateUtil.isValid(str)){
			return str.split(tag);
		}
		return null;
	}

	/**
	 * �ж�values�������Ƿ���value
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
