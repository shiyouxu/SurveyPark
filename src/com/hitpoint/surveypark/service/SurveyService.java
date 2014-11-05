package com.hitpoint.surveypark.service;

import java.util.List;

import com.hitpoint.surveypark.model.Page;
import com.hitpoint.surveypark.model.Question;
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
	
	/**
	 * ���»򱣴�����
	 * @param model
	 */
	public void savaOrUpdateQuestion(Question model);
	
	/**
	 * ɾ�����⣬ͬʱɾ����
	 * @param qid
	 */
	public void deleteQuestion(Integer qid);

	/**
	 * ɾ��ҳ�棬ͬʱɾ������ʹ�
	 */
	public void deletePage(Integer pid);
	
	/**
	 * ɾ�����飬ͬʱɾ��ҳ�桢����ʹ�
	 * @param sid
	 */
	public void deleteSurvey(Integer sid);
	
	/**
	 * �༭����
	 * @param qid
	 * @return
	 */
	public Question getQuestion(Integer qid);
	
	/**
	 * �������
	 * @param sid
	 */
	public void clearAnswers(Integer sid);
	
	/**
	 * �л�����״̬
	 * @param sid
	 */
	public void toggleStatus(Integer sid);
	
	/**
	 * ����logoPhoto·��
	 * @param sid
	 * @param string
	 */
	public void updateLogoPhotoPath(Integer sid, String string);
	
	/**
	 * ��ѯ���鼯�ϣ�Я��pages
	 * @param user
	 * @return
	 */
	public List<Survey> getSurveyWithPages(User user);
	
	/**
	 * ����ҳ���ƶ�/����
	 */
	public void moveOrCopyPage(Integer srcPid, Integer targPid, int pos);

}
