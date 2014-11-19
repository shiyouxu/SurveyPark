package com.hitpoint.surveypark.service;

import java.util.List;

import com.hitpoint.surveypark.model.security.Right;

public interface RightService extends BaseService<Right> {

	public void saveOrUpdateRight(Right model);

	/*
	 *按照url追加权限 
	 */
	public void appendRightByURL(String url);
	
	/**
	 * 批量更新权限
	 */
	public void batchUpdateRights(List<Right> allRights);
	
}
