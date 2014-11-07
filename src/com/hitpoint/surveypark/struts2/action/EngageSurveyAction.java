package com.hitpoint.surveypark.struts2.action;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.ParameterAware;
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
public class EngageSurveyAction extends BaseAction<Survey> implements ServletContextAware,SessionAware,ParameterAware {

	private static final long serialVersionUID = 4779166916915819051L;
	
	//所有参数的map
	private static final String ALL_PARAMS_MAP = "all_params_map";
	
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

	//接收所有参数的map
	private Map<String, String[]> paramsMap;
	
	public Integer getCurrPid() {
		return currPid;
	}

	public void setCurrPid(Integer currPid) {
		this.currPid = currPid;
	}

	private Integer currPid;
	
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
	
	/**
	 * 首次进入参与调查
	 */
	public String entry(){
		//查询首页
		this.currPage = surveyService.getFirstPage(sid);
		//存放survey--->session
		sessionMap.put(CURRENT_SURVEY, currPage.getSurvey());
		//将存放所有答案的大Map放入session中（用于保存答案和回显）
		sessionMap.put(ALL_PARAMS_MAP,new HashMap<Integer, Map<String,String[]>>() );
		
		return "engageSurveyPage";
	}
	
	/**
	 * 处理参与调查
	 */
	public String doEngageSurvey(){
		String submitName = getSubmitName();
		//上一步
		if(submitName.startsWith("pre")){
			mergeParamsIntoSession();
			this.currPage = surveyService.getPrePage(currPid);
			return "engageSurveyPage";
		}
		//下一步
		else if(submitName.startsWith("next")){
			mergeParamsIntoSession();
			this.currPage = surveyService.getNextPage(currPid);
			return "engageSurveyPage";
		}
		//完成
		else if(submitName.startsWith("done")){
			mergeParamsIntoSession();
			//TODO 答案 入库
			clearSessionData();
			return "engageSurveyAction";
		}
		//退出
		else if(submitName.startsWith("exit")){
			clearSessionData();
			return "engageSruveyAction";
		}
		return null;
	}
	
	//清楚session中的数据
	private void clearSessionData() {
		sessionMap.remove(CURRENT_SURVEY);
		sessionMap.remove(ALL_PARAMS_MAP);
	}

	//合并参数到session中
	private void mergeParamsIntoSession() {
		Map<Integer,Map<String,String[]>> allParamsMap = getAllParamsMap();
		allParamsMap.put(currPid, paramsMap);
	}
	
	/**
	 * 获取session中存放的所有参数的map
	 */
	private Map<Integer, Map<String, String[]>> getAllParamsMap() {
		return (Map<Integer, Map<String, String[]>>) sessionMap.get(ALL_PARAMS_MAP);
	}

	/**
	 * 获得提交按钮的名称 
	 */
	private String getSubmitName() {
		for(String key :paramsMap.keySet()){
			if(key.startsWith("submit_")){
				return key;
			}
		}
		return null;
	}

	public void setServletContext(ServletContext arg0) {
		this.sc = arg0;
	}
	

	/**
	 * 注入sessionMap
	 */
	public void setSession(Map<String, Object> session) {
		this.sessionMap = session;
	}
	
	/**
	 * 注入提交的所有参数的map
	 */
	public void setParameters(Map<String, String[]> parameters) {
		this.paramsMap = parameters;
	}

}
