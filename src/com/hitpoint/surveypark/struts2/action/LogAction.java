package com.hitpoint.surveypark.struts2.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hitpoint.surveypark.model.Log;
import com.hitpoint.surveypark.service.LogService;

@Controller
@Scope("prototype")
public class LogAction extends BaseAction<Log> {

	private static final long serialVersionUID = -5611109475828121282L;
	
	@Resource
	private LogService logService;

	private List<Log> allLogs;
	
	/**
	 * 查询全部日志
	 */
	public String findAllLogs(){
		this.allLogs = logService.findAllEntities();
		return "logListPage";
	}
	
}
