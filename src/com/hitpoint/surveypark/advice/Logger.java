package com.hitpoint.surveypark.advice;

import java.util.Map;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;

import com.hitpoint.surveypark.model.Log;
import com.hitpoint.surveypark.model.User;
import com.hitpoint.surveypark.service.LogService;
import com.hitpoint.surveypark.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;

public class Logger {
	
	@Resource
	private LogService logService;
	/**
	 * 记录
	 */
	public Object record(ProceedingJoinPoint pjp){
		Log log = new Log();
		try {
			ActionContext ac = ActionContext.getContext();
			//设置操作人
			if(ac != null){
				Map<String,Object> session = ac.getSession();
				if(session != null){
					User user = (User) session.get("user");
					if(user != null){
						log.setOperator(""+user.getId()+":"+user.getEmail());
						
					}
				}
			}
			//设置操作名称
			String mname = pjp.getSignature().getName();
			log.setOperateName(mname);
			//设置操作参数
			Object[] params = pjp.getArgs();
			log.setOperateParame(StringUtil.arr2Str(params));
			//设置目标对象的方法
			Object ret = pjp.proceed();
			//设置操作结果
			log.setOperateResult("success");
			//设置结果消息
			if(ret != null){
				log.setResultMsg(ret.toString());
			}
			return ret;
		} catch (Throwable e) {
			log.setOperateResult("failure");
			log.setResultMsg(e.getMessage());
		} finally{
			logService.saveEntity(log);
		}
		return null;
	}
}
