package com.hitpoint.surveypark.model;

import java.util.Date;
/**
 * Answer实体类
 * @author leo.zhang
 *
 */
public class Answer {
	private Integer id;
	private String answerIds;//选项的索引
	private String otherAnswer;
	private String uuid;
	private Date answerTime;
	
	private Integer questionid;//(关联字段)
	private Integer surveyid;//关联字段（冗余）
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAnswerIds() {
		return answerIds;
	}
	public void setAnswerIds(String answerIds) {
		this.answerIds = answerIds;
	}
	public String getOtherAnswer() {
		return otherAnswer;
	}
	public void setOtherAnswer(String otherAnswer) {
		this.otherAnswer = otherAnswer;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Date getAnswerTime() {
		return answerTime;
	}
	public void setAnswerTime(Date answerTime) {
		this.answerTime = answerTime;
	}
	public Integer getQuestionid() {
		return questionid;
	}
	public void setQuestionid(Integer questionid) {
		this.questionid = questionid;
	}
	public Integer getSurveyid() {
		return surveyid;
	}
	public void setSurveyid(Integer surveyid) {
		this.surveyid = surveyid;
	}
	
}
