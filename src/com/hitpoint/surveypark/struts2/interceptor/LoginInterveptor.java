package com.hitpoint.surveypark.struts2.interceptor;

import com.hitpoint.surveypark.model.User;
import com.hitpoint.surveypark.struts2.UserAware;
import com.hitpoint.surveypark.struts2.action.BaseAction;
import com.hitpoint.surveypark.struts2.action.LoginAction;
import com.hitpoint.surveypark.struts2.action.RegAction;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * ��¼������
 * @author Administrator
 *
 */
public class LoginInterveptor implements Interceptor {

	public void destroy() {

	}

	public void init() {
	}

	public String intercept(ActionInvocation arg0) throws Exception {
		BaseAction action = (BaseAction) arg0.getAction();
		if(action instanceof LoginAction || action instanceof RegAction){
			return arg0.invoke();
		}else{
			User user = (User) arg0.getInvocationContext().getSession().get("user");
			if(user == null){
				return "login";
			}else{
				//����
				if(action instanceof UserAware){
					//ע��User��action
					((UserAware)action).setUser(user);
				}
				return arg0.invoke();
			}
		}
	}
}
