package com.hitpoint.surveypark.struts2.action;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hitpoint.surveypark.model.Page;
import com.hitpoint.surveypark.model.Survey;
import com.hitpoint.surveypark.service.SurveyService;
import com.hitpoint.surveypark.util.ValidateUtil;

@Controller
@Scope("prototype")
public class EngageSurveyAction extends BaseAction<Survey> implements ServletContextAware,SessionAware {

	private static final long serialVersionUID = 4779166916915819051L;
	
	//当前调查的key
	private static final String CURRENT_SURVEY = "current_survey";
	
	private List<Survey> surveys ;
	
	@Resource
	private SurveyService surveyService;

	private ServletContext sc;
	
	private Integer sid;
	
	//当前页面
	private Page currPage;

	private Map<String, Object> sessionMap;
	
	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public Page getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Page currPage) {
		this.currPage = currPage;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public List<Survey> getSurveys() {
		return surveys;
	}

	public void setSurveys(List<Survey> surveys) {
		this.surveys = surveys;
	}

	/**
	 * 查询所有可用的调查
	 */
	public String findAllAvailableSurveys(){
		this.surveys = surveyService.findAllAvailableSurveys();
		return "engageSurveyListPage";
	}
	
	/**
	 * 获取图片的url地址
	 */
	public String getImageUrl(String path){
		if(ValidateUtil.isValid(path)){
			String absPath = sc.getRealPath(path);
			File f= new File(absPath);
			if(f.exists()){
				return sc.getContextPath() + path;
			}
		}		
		
		return sc.getContextPath() + "/question.bmp";
	}
	
	public void setServletContext(ServletContext arg0) {
		this.sc = arg0;
	}
	
	/**
	 * 首次进入参与调查
	 */
	public String entry(){
		//查询首页
		this.currPage = surveyService.getFirstPage(sid);
		//存放survey--->session
		sessionMap.put(CURRENT_SURVEY, currPage.getSurvey());
		return "engageSurveyPage";
	}

	/**
	 * 注入sessionMap
	 */
	public void setSession(Map<String, Object> session) {
		this.sessionMap = session;
	}

}
