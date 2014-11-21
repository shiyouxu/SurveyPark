package com.hitpoint.surveypark.struts2.interceptor;

import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.hitpoint.surveypark.model.User;
import com.hitpoint.surveypark.model.security.Right;
import com.hitpoint.surveypark.struts2.action.BaseAction;
import com.hitpoint.surveypark.util.ValidateUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * µÇÂ¼À¹½ØÆ÷
 * @author Administrator
 *
 */
public class RightFilterInterceptor implements Interceptor {

	private static final long serialVersionUID = -5051502139802626652L;

	public void destroy() {

	}

	public void init() {
	}

	@SuppressWarnings({ "rawtypes"})
	public String intercept(ActionInvocation arg0) throws Exception {
		BaseAction action = (BaseAction) arg0.getAction();
		ActionProxy proxy = arg0.getProxy();
		String ns = proxy.getNamespace();
		String actionName = proxy.getActionName();
		if(ValidateUtil.hasRight(ns, actionName, ServletActionContext.getRequest(),action)){
			return arg0.invoke();
		}
		return "login";
	}
}
