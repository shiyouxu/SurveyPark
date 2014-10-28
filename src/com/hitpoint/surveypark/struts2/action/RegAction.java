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
@Scope("prototype")//��ʾaction��ԭ��bean
public class RegAction extends BaseAction<User> {
	/**
	 * ���ڷ����л�
	 */
	private static final long serialVersionUID = 6354516232060456701L;
	private User model = new User();
	private String confirmPassword;
	//ע���û�service
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
	 * �����û�ע��
	 */
	public String doReg(){
		//�������
		model.setPassword(DataUtil.md5(model.getPassword()));
		userService.saveEntity(model);
		return "success";
	}
	
	/**
	 * У��
	 */
	public void validate(){
		//1������ǿ�
		String email = model.getEmail();
		if(!ValidateUtil.isValid(model.getEmail())){
			addFieldError("email", "email�Ǳ�����");
		}
		if(!ValidateUtil.isValid(model.getPassword())){
			addFieldError("password", "password�Ǳ�����");
		}
		if(!ValidateUtil.isValid(model.getNickname())){
			addFieldError("nickname", "nickname�Ǳ�����");
		}
		if(hasErrors()){
			return ;
		}
		//2������һ����
		if(!model.getPassword().equals(confirmPassword)){
			addFieldError("password", "�������벻һ��!");
			return ;
		}
		//3��emailռ��
		if(userService.isRegisted(model.getEmail())){
			addFieldError("email", "email��ռ��!");
		}
	}
}
