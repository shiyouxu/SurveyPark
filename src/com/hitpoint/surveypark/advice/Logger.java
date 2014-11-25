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
	 * ��¼
	 */
	public Object record(ProceedingJoinPoint pjp){
		Log log = new Log();
		try {
			ActionContext ac = ActionContext.getContext();
			//���ò�����
			if(ac != null){
				Map<String,Object> session = ac.getSession();
				if(session != null){
					User user = (User) session.get("user");
					if(user != null){
						log.setOperator(""+user.getId()+":"+user.getEmail());
						
					}
				}
			}
			//���ò�������
			String mname = pjp.getSignature().getName();
			log.setOperateName(mname);
			//���ò�������
			Object[] params = pjp.getArgs();
			log.setOperateParame(StringUtil.arr2Str(params));
			//����Ŀ�����ķ���
			Object ret = pjp.proceed();
			//���ò������
			log.setOperateResult("success");
			//���ý����Ϣ
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
