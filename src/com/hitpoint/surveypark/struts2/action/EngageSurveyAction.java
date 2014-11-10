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
		System.out.println(submitName);
		//��һ��
		if(submitName.endsWith("pre")){
			mergeParamsIntoSession();
			this.currPage = surveyService.getPrePage(currPid);
			return "engageSurveyPage";
		}
		//��һ��
		else if(submitName.endsWith("next")){
			mergeParamsIntoSession();
			this.currPage = surveyService.getNextPage(currPid);
			return "engageSurveyPage";
		}
		//���
		else if(submitName.endsWith("done")){
			mergeParamsIntoSession();
			//�� ���
			surveyService.saveAnswers(processAnswers());
			processAnswers();
			clearSessionData();
			return "engageSurveyAction";
		}
		//�˳�
		else if(submitName.endsWith("exit")){
			clearSessionData();
			return "engageSruveyAction";
		}
		return null;
	}
	
	/**
	 * �����
	 */
	private List<Answer> processAnswers() {
		//����ʽ��ѡ��ť
		Map<Integer,String> matrixRadioMap = new HashMap<Integer, String>();
		//���д𰸵ļ���s
		List<Answer> answers = new ArrayList<Answer>();
		Answer a = null;
		String key = null;
		String[] value = null;
		Map<Integer,Map<String,String[]>> allMap = getAllParamsMap();
		for(Map<String,String[]> map :allMap.values()){
			for(Entry<String,String[]> entry:map.entrySet()){
				key = entry.getKey();
				value = entry.getValue();
				//��������q��ͷ�Ĳ���
				if(key.startsWith("q")){
					//�������
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
		//�����������ʽ��ѡ��ť
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
	 * ��ȡ����qid(q1-->1)
	 */
	private Integer getQid(String key) {
		return Integer.parseInt(key.substring(1));
	}

	/**
	 * ��ȡ����ʽ����id(q8_0_1-->8)
	 */
	private Integer getMatrixRadioQid(String key) {
		return Integer.parseInt(key.substring(1,key.indexOf("_")));
	}

	/**
	 * ��ȡ��ǰ�������
	 */
	private Survey getCurrentSurvey() {
		return (Survey) sessionMap.get(CURRENT_SURVEY);
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
	
	/**
	 * ���ñ�ǣ����ڴ𰸵Ļ���
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
	 * ���ñ�ǣ����ڴ𰸻��ԣ������ı������
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
		for (String s : paramsMap.keySet()) {
			System.out.println(s);
		}
	}

}
