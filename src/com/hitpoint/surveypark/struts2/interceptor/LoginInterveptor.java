package com.hitpoint.surveypark.struts2.interceptor;

import com.hitpoint.surveypark.model.User;
import com.hitpoint.surveypark.struts2.UserAware;
import com.hitpoint.surveypark.struts2.action.BaseAction;
import com.hitpoint.surveypark.struts2.action.LoginAction;
import com.hitpoint.surveypark.struts2.action.RegAction;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * 登录拦截器
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
				//放行
				if(action instanceof UserAware){
					//注入User给action
					((UserAware)action).setUser(user);
				}
				return arg0.invoke();
			}
		}
	}
}
