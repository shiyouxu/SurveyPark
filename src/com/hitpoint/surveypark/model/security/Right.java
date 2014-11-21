package com.hitpoint.surveypark.model.security;

import com.hitpoint.surveypark.model.BaseEntity;

public class Right extends BaseEntity {
	private static final long serialVersionUID = -2160355304324221751L;
	private Integer id;
	private String rightName = "δ����";
	private String rightUrl;
	private String rightDesc;
	private long rightCode;//Ȩ���� 1<<n
	private int rightPos;//Ȩ��λ,�൱��Ȩ�޷��飬��0��ʼ
	private boolean common;//�Ƿ��ǹ�����Դ
	
	public boolean isCommon() {
		return common;
	}
	public void setCommon(boolean common) {
		this.common = common;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRightName() {
		return rightName;
	}
	public void setRightName(String rightName) {
		this.rightName = rightName;
	}
	public String getRightUrl() {
		return rightUrl;
	}
	public void setRightUrl(String rightUrl) {
		this.rightUrl = rightUrl;
	}
	public String getRightDesc() {
		return rightDesc;
	}
	public void setRightDesc(String rightDesc) {
		this.rightDesc = rightDesc;
	}
	public long getRightCode() {
		return rightCode;
	}
	public void setRightCode(long rightCode) {
		this.rightCode = rightCode;
	}
	public int getRightPos() {
		return rightPos;
	}
	public void setRightPos(int rightPos) {
		this.rightPos = rightPos;
	}
	
	
}
