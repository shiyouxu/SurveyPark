package com.hitpoint.surveypark.service;

import java.util.List;

import com.hitpoint.surveypark.model.security.Right;

public interface RightService extends BaseService<Right> {

	public void saveOrUpdateRight(Right model);

	/*
	 *����url׷��Ȩ�� 
	 */
	public void appendRightByURL(String url);
	
	/**
	 * ��������Ȩ��
	 */
	public void batchUpdateRights(List<Right> allRights);
	
}
