package com.hitpoint.surveypark.model;

import java.util.Date;

public class Log extends BaseEntity {
	private Integer id;
	private String operator;//操作人
	private String operateName;//操作名称，方法名
	private String operateParame;//操作参数
	private String operateResult;//操作结果，成功/失败
	private String resultMsg;//结果消息
	private Date operateTime = new Date();//操作时间
	
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperateName() {
		return operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	public String getOperateParame() {
		return operateParame;
	}

	public void setOperateParame(String operateParame) {
		this.operateParame = operateParame;
	}

	public String getOperateResult() {
		return operateResult;
	}

	public void setOperateResult(String operateResult) {
		this.operateResult = operateResult;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
}
