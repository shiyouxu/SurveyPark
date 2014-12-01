package com.hitpoint.surveypark.util;

import java.util.Calendar;

public class LogUtil {
	
	/**
	 * 生成日志表名称
	 * offset:偏移量
	 */
	public static String generateLogTableName(int offset) {
		Calendar c = Calendar.getInstance();
		// 当前年份
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
