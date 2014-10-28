package com.hitpoint.surveypark.struts2.action;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hitpoint.surveypark.model.User;
import com.hitpoint.surveypark.service.UserService;
import com.hitpoint.surveypark.util.DataUtil;
import com.hitpoint.surveypark.util.ValidateUtil;

@Controller
@Scope("prototype")//表示action是原型bean
public class RegAction extends BaseAction<User> {
	/**
	 * 用于反序列化
	 */
	private static final long serialVersionUID = 6354516232060456701L;
	private User model = new User();
	private String confirmPassword;
	//注入用户service
	@Resource
	private UserService userService;
	
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public User getModel() {
		return model;
	}
	
	@SkipValidation
	public String toRegPage(){
		return "regPage";
	}
	
	
	/**
	 * 进行用户注册
	 */
	public String doReg(){
		//密码加密
		model.setPassword(DataUtil.md5(model.getPassword()));
		userService.saveEntity(model);
		return "success";
	}
	
	/**
	 * 校验
	 */
	public void validate(){
		//1、密码非空
		String email = model.getEmail();
		if(!ValidateUtil.isValid(model.getEmail())){
			addFieldError("email", "email是必填项");
		}
		if(!ValidateUtil.isValid(model.getPassword())){
			addFieldError("password", "password是必填项");
		}
		if(!ValidateUtil.isValid(model.getNickname())){
			addFieldError("nickname", "nickname是必填项");
		}
		if(hasErrors()){
			return ;
		}
		//2、密码一致性
		if(!model.getPassword().equals(confirmPassword)){
			addFieldError("password", "两次密码不一致!");
			return ;
		}
		//3、email占用
		if(userService.isRegisted(model.getEmail())){
			addFieldError("email", "email已占用!");
		}
	}
}
