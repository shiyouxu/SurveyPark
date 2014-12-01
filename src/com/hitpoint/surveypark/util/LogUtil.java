package com.hitpoint.surveypark.util;

import java.util.Calendar;

public class LogUtil {
	
	/**
	 * ������־������
	 * offset:ƫ����
	 */
	public static String generateLogTableName(int offset) {
		Calendar c = Calendar.getInstance();
		// ��ǰ���
		int year = c.get(Calendar.YEAR);
		//Calendar.MONTH (0~11)
		int month = c.get(Calendar.MONTH) + 1 + offset;
		
		if(month > 12){
			year ++;
			month = month -12;
		}
		
		if(month < 1){
			year --;
			month = month + 12;
		}
		return "logs_" + year + "_" + month ;
	}

}
