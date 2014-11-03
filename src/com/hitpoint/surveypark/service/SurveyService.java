package com.hitpoint.surveypark.service;

import java.util.List;

import com.hitpoint.surveypark.model.Page;
import com.hitpoint.surveypark.model.Survey;
import com.hitpoint.surveypark.model.User;

public interface SurveyService {
	/**
	 * ��ѯ�����б�
	 * @param user
	 * @return
	 */
	public List<Survey> findMySurveys(User user);
	
	/**
	 * �½�����
	 * @param user
	 * @return
	 */
	public Survey newSurvey(User user);
	
	/**
	 * ����id��ѯSurvey����
	 * @param sid
	 * @return
	 */
	public Survey getSurvey(Integer sid);
	
	/**
	 * ����id��ѯSurvey����,ͬʱЯ�����еĺ���
	 * @param sid
	 * @return
	 */
	public Survey getSurveyWithChildren(Integer sid);
	
	/**
	 * ���µ���
	 * @param model
	 */
	public void updateSurvey(Survey model);
	
	/**
	 * ��������ҳ��
	 * @param model
	 */
	public void savaOrUpdatePage(Page model);
	
	/**
	 * ����id��ѯҳ��
	 * @param pid
	 * @return
	 */
	public Page getPage(Integer pid);

}
