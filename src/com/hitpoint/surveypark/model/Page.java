package com.hitpoint.surveypark.model;

import java.util.HashSet;
import java.util.Set;

public class Page extends BaseEntity{
	private static final long serialVersionUID = 9133200376896999468L;
	private Integer id;
	private String title = "未命名";
	private String description;
	//页序
	private float orderno;
	
	public float getOrderno() {
		return orderno;
	}
	public void setOrderno(float orderno) {
		this.orderno = orderno;
	}
	//建立从Page到Survey之间多对一的关联关系
	//transient：临时的；深度复制时不会复制该对象
	private transient Survey survey;
	
	//建立从Page到Question之间一对多关联关系
	private Set<Question> questions = new HashSet<Question>();
	
	public Survey getSurvey() {
		return survey;
	}
	public void setSurvey(Survey survey) {
		this.survey = survey;
	}
	public Set<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
		if(id!=null){
			this.orderno = id;
		}
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
