package com.hitpoint.surveypark.model;

import java.util.Date;

public class Survey {
	private Integer id;
	private String title = "未命名";
	private String preText = "上一步";
	private String nextText = "下一步";
	private String exitText = "退出";
	private String doneText = "完成";
	private Date createTime = new Date();
	//建立从survey到user之间多对1的关联关系
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	//logo路径
	private String logoPhotoPath;
	
	//最大页序
	private String maxOrderno;
	//最小页序
	private String minOrderno;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPreText() {
		return preText;
	}
	public void setPreText(String preText) {
		this.preText = preText;
	}
	public String getNextText() {
		return nextText;
	}
	public void setNextText(String nextText) {
		this.nextText = nextText;
	}
	public String getExitText() {
		return exitText;
	}
	public void setExitText(String exitText) {
		this.exitText = exitText;
	}
	public String getDoneText() {
		return doneText;
	}
	public void setDoneText(String doneText) {
		this.doneText = doneText;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getLogoPhotoPath() {
		return logoPhotoPath;
	}
	public void setLogoPhotoPath(String logoPhotoPath) {
		this.logoPhotoPath = logoPhotoPath;
	}
	public String getMaxOrderno() {
		return maxOrderno;
	}
	public void setMaxOrderno(String maxOrderno) {
		this.maxOrderno = maxOrderno;
	}
	public String getMinOrderno() {
		return minOrderno;
	}
	public void setMinOrderno(String minOrderno) {
		this.minOrderno = minOrderno;
	}
	
}
