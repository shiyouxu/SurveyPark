package com.hitpoint.surveypark.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hitpoint.surveypark.dao.BaseDao;
import com.hitpoint.surveypark.model.Log;
import com.hitpoint.surveypark.service.LogService;

@Service("logService")
public class LogServiceImpl extends BaseServiceImpl<Log> implements LogService {
	
	@Resource(name="logDao")
	public void setDao(BaseDao<Log> dao){
		super.setDao(dao);
	}
}
