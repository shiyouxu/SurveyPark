package com.hitpoint.surveypark.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hitpoint.surveypark.dao.BaseDao;
import com.hitpoint.surveypark.model.security.Right;
import com.hitpoint.surveypark.service.RightService;

@Service("rightService")
public class RightSerivceImpl extends BaseServiceImpl<Right> implements RightService {
	
	@Resource(name="rightDao")
	public void setDao(BaseDao<Right> dao){
		super.setDao(dao);
	}
}
