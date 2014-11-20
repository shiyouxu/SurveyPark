package com.hitpoint.surveypark.service;

import java.util.List;
import java.util.Set;

import com.hitpoint.surveypark.model.security.Role;

public interface RoleService extends BaseService<Role> {
	
	/**
	 * ����/���½�ɫ
	 */
	public void saveOrUpdateRole(Role model, Integer[] ownRightIds);
	
	/**
	 * ��ѯ�û�û�еĽ�ɫ����
	 */
	public List<Role> findRolesNotInRange(Set<Role> roles);
	
}
