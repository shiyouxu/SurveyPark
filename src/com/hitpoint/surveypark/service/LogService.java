package com.hitpoint.surveypark.service;

import com.hitpoint.surveypark.model.Log;

public interface LogService extends BaseService<Log> {
	
	/**
	 * ͨ������������־��
	 */
	public void createLogTable(String tableName);
}
