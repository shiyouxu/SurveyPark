package com.hitpoint.surveypark.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.hitpoint.surveypark.model.security.Right;
import com.hitpoint.surveypark.model.security.Role;

public class User extends BaseEntity {
	private static final long serialVersionUID = -3023429159818521512L;
	private Integer id;
	private String email;
	private String password;
	private String nickname;
	//ע��ʱ��
	private Date regDate = new Date();
	//��ɫ����
	private Set<Role> roles = new HashSet<Role>();
	//Ȩ���ܺ�
	private long[] rightSum;
	//�Ƿ��ǳ�������Ա
	private boolean superAdmin;
	
	public boolean isSuperAdmin() {
		return superAdmin;
	}
	public void setSuperAdmin(boolean superAdmin) {
		this.superAdmin = superAdmin;
	}
	public long[] getRightSum() {
		return rightSum;
	}
	public void setRightSum(long[] rightSum) {
		this.rightSum = rightSum;
	}
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
	
	/**
	 * �����û�Ȩ���ܺ�
	 */
	public void calculateRightSum() {
		int pos = 0;
		long code = 0;
		for(Role role :roles){
			//�ж��Ƿ��ǳ�������Ա
			if("-1".equals(role.getRoleValue())){
				this.superAdmin = true;
				//�ͷ���Դ
				roles = null ;
				return ;
			}
			for(Right r : role.getRights()){
				pos = r.getRightPos();
				code = r.getRightCode();
				rightSum[pos] = rightSum[pos] | code;
			}
		}
		//�ͷ���Դ
		roles = null;
	}
	
	/**
	 * �ж��û��Ƿ����ָ��Ȩ��
	 */
	public boolean hasRight(Right r) {
		int pos = r.getRightPos();
		long code = r.getRightCode();
		return (rightSum[pos] & code ) != 0;
	}
	
	
}
