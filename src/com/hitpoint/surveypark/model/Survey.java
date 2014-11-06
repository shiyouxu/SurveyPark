package com.hitpoint.surveypark.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Survey implements Serializable{
	private static final long serialVersionUID = -9196454747890644210L;
	private Integer id;
	private String title = "未命名";
	private String preText = "上一步";
	private String nextText = "下一步";
	private String exitText = "退出";
	private String doneText = "完成";
	
	private float minOrderno;
	private float maxOrderno;
	public float getMinOrderno() {
		return minOrderno;
	}
	public void setMinOrderno(float minOrderno) {
		this.minOrderno = minOrderno;
	}
	public float getMaxOrderno() {
		return maxOrderno;
	}
	public void setMaxOrderno(float maxOrderno) {
		this.maxOrderno = maxOrderno;
	}
	private Date createTime = new Date();
	//是否关闭调查
	private boolean closed;
	private String logoPhotoPath;
	
	public String getLogoPhotoPath() {
		return logoPhotoPath;
	}
	public void setLogoPhotoPath(String logoPhotoPath) {
		this.logoPhotoPath = logoPhotoPath;
	}
	public boolean isClosed() {
		return closed;
	}
	public void setClosed(boolean closed) {
		this.closed = closed;
	}
	private User user;
	//建立从survey到user之间多对1的关联关系
	
	private Set<Page> pages = new HashSet<Page>();
	
	public Set<Page> getPages() {
		return pages;
	}
	public void setPages(Set<Page> pages) {
		this.pages = pages;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
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
	
	
}
