package com.hitpoint.surveypark.service;

import java.util.List;
import java.util.Set;

import com.hitpoint.surveypark.model.security.Role;

public interface RoleService extends BaseService<Role> {
	
	/**
	 * 保存/更新角色
	 */
	public void saveOrUpdateRole(Role model, Integer[] ownRightIds);
	
	/**
	 * 查询用户没有的角色集合
	 */
	public List<Role> findRolesNotInRange(Set<Role> roles);
	
}
