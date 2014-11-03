package com.hitpoint.surveypark.struts2.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hitpoint.surveypark.model.Page;
import com.hitpoint.surveypark.model.Question;
import com.hitpoint.surveypark.service.SurveyService;

@Controller
@Scope("prototype")
public class QuestionAction extends BaseAction<Question> {

	private static final long serialVersionUID = -1599266758400166075L;
	
	private Integer pid;
	
	private Integer sid;
	
	@Resource
	private SurveyService surveyService;
	
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	/**
	 * ����ѡ������ҳ��
	 * @return
	 */
	public String toSelectQuestionType(){
		return "selectQuestionTypePage";
	}
	
	/**
	 * �����������ҳ��
	 * @return
	 */
	public String toDesignQuestionPage(){
		return ""+model.getQuestionType();
	}
	
	/**
	 * ������������
	 * @return
	 */
	public String saveOrUpdateQuestion(){
		Page p = new Page();
		p.setId(pid);
		model.setPage(p);
		surveyService.savaOrUpdateQuestion(model);
		return "designSurveyAction";
	}
	
}
