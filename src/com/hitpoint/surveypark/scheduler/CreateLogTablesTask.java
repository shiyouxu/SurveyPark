package com.hitpoint.surveypark.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.hitpoint.surveypark.service.LogService;
import com.hitpoint.surveypark.util.LogUtil;

/**
 * 创建日志表的石英任务
 */
public class CreateLogTablesTask extends QuartzJobBean {

	private LogService logService;
	
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	/**
	 * 生成日志表
	 */
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		
		//下一月
		String tableName = LogUtil.generateLogTableName(1);
		logService.createLogTable(tableName);
		
		//下两月
		tableName = LogUtil.generateLogTableName(2);
		logService.createLogTable(tableName);
		
		//下三月
		tableName = LogUtil.generateLogTableName(3);
		logService.createLogTable(tableName);
	}

}
