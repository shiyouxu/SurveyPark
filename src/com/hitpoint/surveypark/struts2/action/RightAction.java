package com.hitpoint.surveypark.struts2.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hitpoint.surveypark.model.security.Right;
import com.hitpoint.surveypark.service.RightService;

@Controller
@Scope("prototype")
public class RightAction extends BaseAction<Right> {

	private static final long serialVersionUID = -4075607000997956819L;

	private List<Right> allRights;
	
	@Resource
	private RightService rightService ;
	

	public RightService getRightService() {
		return rightService;
	}

	public void setRightService(RightService rightService) {
		this.rightService = rightService;
	}

	
	/**
	 * 查询所有权限
	 */
	public String findAllRights(){
		this.allRights = rightService.findAllEntities();
		return "rightListPage";
	}

	public List<Right> getAllRights() {
		return allRights;
	}
	
	/**
	 * 查询所有权限
	 */
	public void setAllRights(List<Right> allRights) {
		this.allRights = allRights;
	}
	
	/**
	 * 添加权限
	 */
	public String toAddRightPage(){
		return "addRightPage";
	}
}
