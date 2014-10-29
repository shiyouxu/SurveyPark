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
	
	//注入surveyService
	@Resource
	private SurveyService surveyService;
	
	//调查集合
	private List<Survey> mySurveys;
	
	//接收sessionMap
	//private Map<String, Object> sessionMap;
	
	//接收user对象
	private User user;

	public List<Survey> getMySurveys() {
		return mySurveys;
	}

	public void setMySurveys(List<Survey> mySurveys) {
		this.mySurveys = mySurveys;
	}

	/**
	 * 查询我的调查列表
	 * @return
	 */
	public String mySurveys(){
		//User user = (User) sessionMap.get("user");
		this.mySurveys = surveyService.findMySurveys(user);
		return "mySurveyListPage";
	}
	
	/**
	 * 新建调查
	 * @return
	 */
	public String newSurveys(){
		//User user = (User) sessionMap.get("user");
		this.model = surveyService.newSurvey(user);
		return "designSurveyPage";
	}
	
	//注入user对象
	public void setUser(User user) {
		this.user = user;
	}
}
