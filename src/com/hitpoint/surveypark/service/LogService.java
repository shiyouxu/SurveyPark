package com.hitpoint.surveypark.service;

import com.hitpoint.surveypark.model.Log;

public interface LogService extends BaseService<Log> {
	
	/**
	 * 通过表名创建日志表
	 */
	public void createLogTable(String tableName);
}
