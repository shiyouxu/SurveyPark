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
	
	//���в�����map
	private static final String ALL_PARAMS_MAP = "all_params_map";
	
	//��ǰ�����key
	private static final String CURRENT_SURVEY = "current_survey";
	
	private List<Survey> surveys ;
	
	@Resource
	private SurveyService surveyService;

	private ServletContext sc;
	
	private Integer sid;
	
	//��ǰҳ��
	private Page currPage;

	private Map<String, Object> sessionMap;

	//�������в�����map
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
	 * ��ѯ���п��õĵ���
	 */
	public String findAllAvailableSurveys(){
		this.surveys = surveyService.findAllAvailableSurveys();
		return "engageSurveyListPage";
	}
	
	/**
	 * ��ȡͼƬ��url��ַ
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
	 * �״ν���������
	 */
	public String entry(){
		//��ѯ��ҳ
		this.currPage = surveyService.getFirstPage(sid);
		//���survey--->session
		sessionMap.put(CURRENT_SURVEY, currPage.getSurvey());
		//��������д𰸵Ĵ�Map����session�У����ڱ���𰸺ͻ��ԣ�
		sessionMap.put(ALL_PARAMS_MAP,new HashMap<Integer, Map<String,String[]>>() );
		
		return "engageSurveyPage";
	}
	
	/**
	 * ����������
	 */
	public String doEngageSurvey(){
		String submitName = getSubmitName();
		//��һ��
		if(submitName.startsWith("pre")){
			mergeParamsIntoSession();
			this.currPage = surveyService.getPrePage(currPid);
			return "engageSurveyPage";
		}
		//��һ��
		else if(submitName.startsWith("next")){
			mergeParamsIntoSession();
			this.currPage = surveyService.getNextPage(currPid);
			return "engageSurveyPage";
		}
		//���
		else if(submitName.startsWith("done")){
			mergeParamsIntoSession();
			//TODO �� ���
			clearSessionData();
			return "engageSurveyAction";
		}
		//�˳�
		else if(submitName.startsWith("exit")){
			clearSessionData();
			return "engageSruveyAction";
		}
		return null;
	}
	
	//���session�е�����
	private void clearSessionData() {
		sessionMap.remove(CURRENT_SURVEY);
		sessionMap.remove(ALL_PARAMS_MAP);
	}

	//�ϲ�������session��
	private void mergeParamsIntoSession() {
		Map<Integer,Map<String,String[]>> allParamsMap = getAllParamsMap();
		allParamsMap.put(currPid, paramsMap);
	}
	
	/**
	 * ��ȡsession�д�ŵ����в�����map
	 */
	private Map<Integer, Map<String, String[]>> getAllParamsMap() {
		return (Map<Integer, Map<String, String[]>>) sessionMap.get(ALL_PARAMS_MAP);
	}

	/**
	 * ����ύ��ť������ 
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
	 * ע��sessionMap
	 */
	public void setSession(Map<String, Object> session) {
		this.sessionMap = session;
	}
	
	/**
	 * ע���ύ�����в�����map
	 */
	public void setParameters(Map<String, String[]> parameters) {
		this.paramsMap = parameters;
	}

}
