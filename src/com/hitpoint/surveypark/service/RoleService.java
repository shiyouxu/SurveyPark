package com.hitpoint.surveypark.service;

import java.util.List;

import com.hitpoint.surveypark.model.security.Role;

public interface RoleService extends BaseService<Role> {
	
	/**
	 * 保存/更新角色
	 */
	void saveOrUpdateRole(Role model, Integer[] ownRightIds);
	
}
