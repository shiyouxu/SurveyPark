package com.hitpoint.surveypark.struts2.interceptor;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hitpoint.surveypark.service.RightService;
import com.hitpoint.surveypark.util.ValidateUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * 捕获url的拦截器
 */
public class CatchUrlInterceptor implements Interceptor {

	private static final long serialVersionUID = 2348196713382435282L;

	public void destroy() {

	}

	public void init() {

	}

	public String intercept(ActionInvocation invocation) throws Exception {
		ActionProxy proxy = invocation.getProxy();
		String ns = proxy.getNamespace();
		String actionName = proxy.getActionName();
		if(ValidateUtil.isValid(ns) || ns.equals("/")){
			ns = "";
		}
		String url = ns + "/" + actionName;
		//取得在application中的spring容器
		//方式一
		//ApplicationContext ac = (ApplicationContext) invocation.getInvocationContext().getApplication().get(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		//方式二
		ServletContext sc = ServletActionContext.getServletContext();
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sc);

		RightService rs = (RightService) ac.getBean("rightService");
		rs.appendRightByURL(url);
		return invocation.invoke();
	}

}
