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
	
	//接收session的map
	private Map<String, Object> sessionMap;
	
	@Resource
	private UserService userService;
	
	/*
	 * 到达登陆界面
	 */
	public String toLoginPage(){
		return "loginPage";
	}
	
	/**
	 * 校验登陆信息
	 */
	public void validateDoLogin(){
		User user = userService.validateLoginInfo(model.getEmail(),DataUtil.md5(model.getPassword()));
		if(user == null){
			addActionError("email/password错误");
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
