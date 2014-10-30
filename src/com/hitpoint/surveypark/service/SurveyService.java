package com.hitpoint.surveypark.service;

import java.util.List;

import com.hitpoint.surveypark.model.Survey;
import com.hitpoint.surveypark.model.User;

public interface SurveyService {
	/**
	 * 查询调查列表
	 * @param user
	 * @return
	 */
	public List<Survey> findMySurveys(User user);
	
	/**
	 * 新建调查
	 * @param user
	 * @return
	 */
	public Survey newSurvey(User user);
	
	/**
	 * 按照id查询Survey对象
	 * @param sid
	 * @return
	 */
	public Survey getSurvey(Integer sid);

}
