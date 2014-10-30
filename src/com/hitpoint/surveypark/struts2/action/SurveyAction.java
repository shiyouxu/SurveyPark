package com.hitpoint.surveypark.struts2.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hitpoint.surveypark.model.Survey;
import com.hitpoint.surveypark.model.User;
import com.hitpoint.surveypark.service.SurveyService;
import com.hitpoint.surveypark.struts2.UserAware;
@Controller
@Scope("prototype")
public class SurveyAction extends BaseAction<Survey> implements UserAware {

	private static final long serialVersionUID = -2508803636993650070L;
	
	//ע��surveyService
	@Resource
	private SurveyService surveyService;
	
	//���鼯��
	private List<Survey> mySurveys;
	
	//����sessionMap
	//private Map<String, Object> sessionMap;
	
	//����user����
	private User user;
	
	//����sid����
	private Integer sid;
	
	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public List<Survey> getMySurveys() {
		return mySurveys;
	}

	public void setMySurveys(List<Survey> mySurveys) {
		this.mySurveys = mySurveys;
	}

	/**
	 * ��ѯ�ҵĵ����б�
	 * @return
	 */
	public String mySurveys(){
		//User user = (User) sessionMap.get("user");
		System.out.println(user.getId());
		this.mySurveys = surveyService.findMySurveys(user);
		return "mySurveyListPage";
	}
	
	/**
	 * �½�����
	 * @return
	 */
	public String newSurvey(){
		//User user = (User) sessionMap.get("user");
		this.model = surveyService.newSurvey(user);
		return "designSurveyPage";
	}
	
	/**
	 * ��Ƶ���
	 * @return
	 */
	public String designSurvey(){
		this.model = surveyService.getSurveyWithChildren(sid);
		return "designSurveyPage";
	}
	
	/**
	 * �÷���ֻ��designSurvey֮ǰ����
	 */
//	public void prepareDesignSurvey(){
//		this.model = surveyService.getSurveyWithChildren(sid);
//	}
	
	//ע��user����
	public void setUser(User user) {
		this.user = user;
	}
}
