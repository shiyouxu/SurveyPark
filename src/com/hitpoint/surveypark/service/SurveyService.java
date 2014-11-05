package com.hitpoint.surveypark.service;

import java.util.List;

import com.hitpoint.surveypark.model.Page;
import com.hitpoint.surveypark.model.Question;
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
	
	/**
	 * 更新或保存问题
	 * @param model
	 */
	public void savaOrUpdateQuestion(Question model);
	
	/**
	 * 删除问题，同时删除答案
	 * @param qid
	 */
	public void deleteQuestion(Integer qid);

	/**
	 * 删除页面，同时删除问题和答案
	 */
	public void deletePage(Integer pid);
	
	/**
	 * 删除调查，同时删除页面、问题和答案
	 * @param sid
	 */
	public void deleteSurvey(Integer sid);
	
	/**
	 * 编辑问题
	 * @param qid
	 * @return
	 */
	public Question getQuestion(Integer qid);
	
	/**
	 * 清楚调查
	 * @param sid
	 */
	public void clearAnswers(Integer sid);
	
	/**
	 * 切换调查状态
	 * @param sid
	 */
	public void toggleStatus(Integer sid);
	
	/**
	 * 更新logoPhoto路径
	 * @param sid
	 * @param string
	 */
	public void updateLogoPhotoPath(Integer sid, String string);
	
	/**
	 * 查询调查集合，携带pages
	 * @param user
	 * @return
	 */
	public List<Survey> getSurveyWithPages(User user);
	
	/**
	 * 进行页面移动/复制
	 */
	public void moveOrCopyPage(Integer srcPid, Integer targPid, int pos);

}
