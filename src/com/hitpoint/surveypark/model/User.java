package com.hitpoint.surveypark.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.hitpoint.surveypark.model.security.Role;

public class User extends BaseEntity {
	private static final long serialVersionUID = -3023429159818521512L;
	private Integer id;
	private String email;
	private String password;
	private String nickname;
	//注册时间
	private Date regDate = new Date();
	//角色集合
	private Set<Role> roles = new HashSet<Role>();
	
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
}
