package com.hitpoint.surveypark.struts2.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hitpoint.surveypark.model.Page;
import com.hitpoint.surveypark.model.Survey;
import com.hitpoint.surveypark.service.SurveyService;

@Controller
@Scope("prototype")
public class PageAction extends BaseAction<Page> {

	private static final long serialVersionUID = -8119527689481508961L;

	private Integer sid;
	
	private Integer pid;
	
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	//ע��surveyService
	@Resource
	private SurveyService surveyService;
	
	
	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	/**
	 * ��������Page��ҳ��
	 * @return
	 */
	public String toAddPage(){
		return "addPagePage";
	}
	
	/**
	 * ����/����ҳ��
	 * @return
	 */
	public String saveOrUpdatePage(){
		Survey s = new Survey();
		s.setId(sid);
		model.setSurvey(s);
		surveyService.savaOrUpdatePage(model);
		return "designSurveyAction";
	}
	
	/**
	 * �༭ҳ��
	 * @return
	 */
	public String editPage(){
		this.model = surveyService.getPage(pid);
		return "editPagePage";
	}
	
}
