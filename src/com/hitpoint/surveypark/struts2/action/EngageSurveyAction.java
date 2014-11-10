package com.hitpoint.surveypark.struts2.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hitpoint.surveypark.model.Answer;
import com.hitpoint.surveypark.model.Page;
import com.hitpoint.surveypark.model.Survey;
import com.hitpoint.surveypark.service.SurveyService;
import com.hitpoint.surveypark.util.StringUtil;
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
	
	private Integer currPid;
	
	public Integer getCurrPid() {
		return currPid;
	}

	public void setCurrPid(Integer currPid) {
		this.currPid = currPid;
	}

	
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
		System.out.println(submitName);
		//上一步
		if(submitName.endsWith("pre")){
			mergeParamsIntoSession();
			this.currPage = surveyService.getPrePage(currPid);
			return "engageSurveyPage";
		}
		//下一步
		else if(submitName.endsWith("next")){
			mergeParamsIntoSession();
			this.currPage = surveyService.getNextPage(currPid);
			return "engageSurveyPage";
		}
		//完成
		else if(submitName.endsWith("done")){
			mergeParamsIntoSession();
			//答案 入库
			surveyService.saveAnswers(processAnswers());
			processAnswers();
			clearSessionData();
			return "engageSurveyAction";
		}
		//退出
		else if(submitName.endsWith("exit")){
			clearSessionData();
			return "engageSruveyAction";
		}
		return null;
	}
	
	/**
	 * 处理答案
	 */
	private List<Answer> processAnswers() {
		//矩阵式单选按钮
		Map<Integer,String> matrixRadioMap = new HashMap<Integer, String>();
		//所有答案的集合s
		List<Answer> answers = new ArrayList<Answer>();
		Answer a = null;
		String key = null;
		String[] value = null;
		Map<Integer,Map<String,String[]>> allMap = getAllParamsMap();
		for(Map<String,String[]> map :allMap.values()){
			for(Entry<String,String[]> entry:map.entrySet()){
				key = entry.getKey();
				value = entry.getValue();
				//处理所有q开头的参数
				if(key.startsWith("q")){
					//常规参数
					if( !key.contains("other") && !key.contains("_")){
						a = new Answer();
						a.setAnswerIds(StringUtil.arr2Str(value));
						a.setQuestionid(getQid(key));
						a.setSurveyid(getCurrentSurvey().getId());
						a.setOtherAnswer(StringUtil.arr2Str(map.get(key+"other")));//otherAnswer
						answers.add(a);
					}
					else if(key.contains("_")){
						Integer radioQid = getMatrixRadioQid(key);
						String oldValue = matrixRadioMap.get(radioQid);
						if(oldValue == null){
							matrixRadioMap.put(radioQid, StringUtil.arr2Str(value));
						}else{
							matrixRadioMap.put(radioQid, oldValue+","+StringUtil.arr2Str(value));
						}
					}
				}
			}
		}
		//单独处理矩阵式单选按钮
		processMatrixRadioMap(matrixRadioMap,answers);
		
		return answers;
	}

	private void processMatrixRadioMap(Map<Integer, String> matrixRadioMap,
			List<Answer> answers) {
		Answer a = null;
		Integer key = null;
		String value = null;
		for(Entry<Integer,String> entry : matrixRadioMap.entrySet()){
			key = entry.getKey();
			value = entry.getValue();
			a = new Answer();
			a.setAnswerIds(value);
			a.setQuestionid(key);
			a.setSurveyid(getCurrentSurvey().getId());
			answers.add(a);
		}
	}
	
	/**
	 * 提取问题qid(q1-->1)
	 */
	private Integer getQid(String key) {
		return Integer.parseInt(key.substring(1));
	}

	/**
	 * 获取矩阵式问题id(q8_0_1-->8)
	 */
	private Integer getMatrixRadioQid(String key) {
		return Integer.parseInt(key.substring(1,key.indexOf("_")));
	}

	/**
	 * 获取当前调查对象
	 */
	private Survey getCurrentSurvey() {
		return (Survey) sessionMap.get(CURRENT_SURVEY);
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
	
	/**
	 * 设置标记，用于答案的回显
	 */
	public String setTag(String name,String value,String selectTag){
		Map<String,String[]> map = getAllParamsMap().get(currPage.getId());
		String[] values = map.get(name);
		if(StringUtil.contains(values,value)){
			return selectTag;
		}
		return "";
	}
	
	/**
	 * 设置标记，用于答案回显，设置文本框回显
	 */
	public String setText(String name){
		Map<String,String[]> map = getAllParamsMap().get(currPage.getId());
		String[] values = map.get(name);
		return "value='"+values[0]+"'";
		
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
		for (String s : paramsMap.keySet()) {
			System.out.println(s);
		}
	}

}
