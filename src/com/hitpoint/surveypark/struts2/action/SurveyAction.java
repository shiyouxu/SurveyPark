package com.hitpoint.surveypark.struts2.action;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hitpoint.surveypark.model.Page;
import com.hitpoint.surveypark.model.Question;
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
		Set<Page> pages = model.getPages();
		for (Page page : pages) {
			System.out.println(page.getTitle()+"----------------------------------");
			Set<Question> questions = page.getQuestions();
			for (Question question : questions) {
				System.out.println(question.getTitle()+"============================");
			}
		}
		return "designSurveyPage";
	}
	
	/**
	 * �༭����
	 * @return
	 */
	public String editSurvey(){
		this.model = surveyService.getSurvey(sid);
		return "editSurveyPage";
	}
	
	/**
	 * ���µ���
	 * @return
	 */
	public String updateSurvey(){
		this.sid = model.getId();
		//���ֹ�����ϵ
		model.setUser(user);
		System.out.println(model.getNextText());
		surveyService.updateSurvey(model);
		return "designSurveyAction";
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
	
	/**
	 * ɾ������
	 * @return
	 */
	public String deleteSurvey(){
		surveyService.deleteSurvey(sid);
		return "findMySurveysAction";
	}
	
	/**
	 * �������Ĵ�
	 * @return
	 */
	public String clearAnswers(){
		surveyService.clearAnswers(sid);
		return "findMySurveysAction";
	}
	
	public String toggleStatus(){
		surveyService.toggleStatus(sid);
		return "findMySurveysAction";
	}
}
