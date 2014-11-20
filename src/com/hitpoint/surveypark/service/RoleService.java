package com.hitpoint.surveypark.service;

import java.util.List;

import com.hitpoint.surveypark.model.security.Role;

public interface RoleService extends BaseService<Role> {
	
	/**
	 * ����/���½�ɫ
	 */
	void saveOrUpdateRole(Role model, Integer[] ownRightIds);
	
}
