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
	
	//注入surveyService
	@Resource
	private SurveyService surveyService;
	
	//调查集合
	private List<Survey> mySurveys;
	
	//接收sessionMap
	//private Map<String, Object> sessionMap;
	
	//接收user对象
	private User user;
	
	//接收sid参数
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
	 * 查询我的调查列表
	 * @return
	 */
	public String mySurveys(){
		//User user = (User) sessionMap.get("user");
		System.out.println(user.getId());
		this.mySurveys = surveyService.findMySurveys(user);
		return "mySurveyListPage";
	}
	
	/**
	 * 新建调查
	 * @return
	 */
	public String newSurvey(){
		//User user = (User) sessionMap.get("user");
		this.model = surveyService.newSurvey(user);
		return "designSurveyPage";
	}
	
	/**
	 * 设计调查
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
	 * 编辑调查
	 * @return
	 */
	public String editSurvey(){
		this.model = surveyService.getSurvey(sid);
		return "editSurveyPage";
	}
	
	/**
	 * 更新调查
	 * @return
	 */
	public String updateSurvey(){
		this.sid = model.getId();
		//保持关联关系
		model.setUser(user);
		System.out.println(model.getNextText());
		surveyService.updateSurvey(model);
		return "designSurveyAction";
	}
	
	/**
	 * 该方法只在designSurvey之前运行
	 */
//	public void prepareDesignSurvey(){
//		this.model = surveyService.getSurveyWithChildren(sid);
//	}
	
	//注入user对象
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * 删除调查
	 * @return
	 */
	public String deleteSurvey(){
		surveyService.deleteSurvey(sid);
		return "findMySurveysAction";
	}
	
	/**
	 * 清除调查的答案
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
