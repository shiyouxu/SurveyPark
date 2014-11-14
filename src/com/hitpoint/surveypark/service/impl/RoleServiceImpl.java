package com.hitpoint.surveypark.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hitpoint.surveypark.dao.BaseDao;
import com.hitpoint.surveypark.model.security.Role;
import com.hitpoint.surveypark.service.RoleService;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

	@Resource(name="roleDao")
	public void setDao(BaseDao<Role> dao){
		super.setDao(dao);
	}
}
