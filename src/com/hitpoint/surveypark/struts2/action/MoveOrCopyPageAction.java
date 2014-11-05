package com.hitpoint.surveypark.struts2.action;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hitpoint.surveypark.model.Page;
import com.hitpoint.surveypark.model.Survey;
import com.hitpoint.surveypark.model.User;
import com.hitpoint.surveypark.service.SurveyService;
import com.hitpoint.surveypark.struts2.UserAware;

@Controller
@Scope("prototype")
public class MoveOrCopyPageAction extends BaseAction<Page> implements UserAware {

	private static final long serialVersionUID = -1929171353397216521L;
	
	//原页面id
	private Integer srcPid;
	
	//目标页面id
	private Integer targPid;
	
	//位置(0:之前；1：之后)
	private int pos;
	
	//目标调查id
	private Integer sid;
	
	public Integer getTargPid() {
		return targPid;
	}

	public void setTargPid(Integer targPid) {
		this.targPid = targPid;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	private List<Survey> mySurveys;
	
	@Resource
	private SurveyService surveyService;

	private User user;
	
	public List<Survey> getMySurveys() {
		return mySurveys;
	}

	public void setMySurveys(List<Survey> mySurveys) {
		this.mySurveys = mySurveys;
	}

	public Integer getSrcPid() {
		return srcPid;
	}

	public void setSrcPid(Integer srcPid) {
		this.srcPid = srcPid;
	}
	
	public String toSelectTargetPage(){
		this.mySurveys = surveyService.getSurveyWithPages(user);
		for (Survey s : mySurveys) {
			System.out.println("survey-------"+s.getTitle());
			Set<Page> pages = s.getPages();
			for (Page page : pages) {
				System.out.println("page---------"+page.getTitle());
			}
		}
		return "moveOrCopyPageListPage";
	}
	
	/**
	 * 进行页面移动/复制
	 */
	public String doMoveOrCopyPage(){
		surveyService.moveOrCopyPage(srcPid,targPid,pos);
		return "designSurveyAction";
	}
	
	//注入User
	public void setUser(User user) {
		this.user = user;
	}
}
