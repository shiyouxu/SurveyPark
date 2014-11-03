package com.hitpoint.surveypark.service;

import java.util.List;

import com.hitpoint.surveypark.model.Page;
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
	
	/**
	 * 按照id查询Survey对象,同时携带所有的孩子
	 * @param sid
	 * @return
	 */
	public Survey getSurveyWithChildren(Integer sid);
	
	/**
	 * 更新调查
	 * @param model
	 */
	public void updateSurvey(Survey model);
	
	/**
	 * 保存或更新页面
	 * @param model
	 */
	public void savaOrUpdatePage(Page model);
	
	/**
	 * 按照id查询页面
	 * @param pid
	 * @return
	 */
	public Page getPage(Integer pid);

}
