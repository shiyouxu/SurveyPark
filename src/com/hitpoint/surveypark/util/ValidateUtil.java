package com.hitpoint.surveypark.util;

import java.util.Collection;

/**
 * У�鹤����
 * @author leo.zhang
 *
 */
public class ValidateUtil {
	/**
	 * �ж��ַ�����Ч��
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
	 * �жϼ��ϵ���Ч��
	 */
	public static boolean isValid(Collection c){
		if(c==null || c.isEmpty()){
			return false;
		}
		return true;
	}
}
