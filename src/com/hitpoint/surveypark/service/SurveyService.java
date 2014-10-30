package com.hitpoint.surveypark.service;

import java.util.List;

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

}
