package com.hitpoint.surveypark.struts2.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hitpoint.surveypark.model.User;
import com.hitpoint.surveypark.service.UserService;
import com.hitpoint.surveypark.util.DataUtil;

@Controller
@Scope("prototype")
public class LoginAction extends BaseAction<User> implements SessionAware {
	
	private static final long serialVersionUID = 7236968929777892968L;
	
	//����session��map
	private Map<String, Object> sessionMap;
	
	@Resource
	private UserService userService;
	
	/*
	 * �����½����
	 */
	public String toLoginPage(){
		return "loginPage";
	}
	
	/**
	 * У���½��Ϣ
	 */
	public void validateDoLogin(){
		User user = userService.validateLoginInfo(model.getEmail(),DataUtil.md5(model.getPassword()));
		if(user == null){
			addActionError("email/password����");
		}else{
			sessionMap.put("user", user);
		}
	}

	public String doLogin(){
		return "success";
	}
	public void setSession(Map<String, Object> arg0) {
		this.sessionMap = arg0;
	}
	
}
