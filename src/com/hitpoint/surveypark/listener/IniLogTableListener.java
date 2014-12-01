package com.hitpoint.surveypark.listener;

import javax.annotation.Resource;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.hitpoint.surveypark.service.LogService;
import com.hitpoint.surveypark.util.LogUtil;

/**
 * ��ʼ����־�������
 */
@Component
public class IniLogTableListener implements ApplicationListener {

	@Resource
	private LogService logService;
	
	public void onApplicationEvent(ApplicationEvent arg0) {
		//������ˢ���¼�
		if(arg0 instanceof ContextRefreshedEvent){
			String tableName = LogUtil.generateLogTableName(0);
			logService.createLogTable(tableName);
			
			tableName = LogUtil.generateLogTableName(0);
			logService.createLogTable(tableName);
			
			tableName = LogUtil.generateLogTableName(0);
			logService.createLogTable(tableName);
			
		}
	}

}
