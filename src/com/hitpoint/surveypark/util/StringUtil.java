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

	/**
	 * ������任���ַ���,ʹ��","�ָ�
	 */
	public static String arr2Str(String[] value) {
		String temp = "";
		if(ValidateUtil.isValid(value)){
			for(String s:value){
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
}
