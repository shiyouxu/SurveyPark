package com.hitpoint.surveypark.service;

import java.util.List;
import java.util.Set;

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
	
	/**
	 * ��ѯ��ָ����Χ�ڵ�Ȩ��
	 */
	public List<Right> findRightsInRange(Integer[] ownRightIds);
	
	/**
	 * ��ѯ����ָ����Χ�ڵ�Ȩ��
	 */
	public List<Right> findRightsNotInRange(Set<Right> ownRightIds);
	
	/**
	 * ��ѯ���Ȩ��λ
	 */
	public int getMaxRightPos();
	
}
