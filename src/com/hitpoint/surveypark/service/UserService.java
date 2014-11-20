package com.hitpoint.surveypark.service;

import com.hitpoint.surveypark.model.User;

public interface UserService extends BaseService<User> {
	/**
	 * �ж�email�Ƿ�ռ��
	 */
	public boolean isRegisted(String email);

	
	/**
	 * ��֤��½��Ϣ
	 * @param email
	 * @param md5
	 * @return
	 */
	public User validateLoginInfo(String email, String md5);

	/**
	 * �����û���Ȩ(ֻ�ܸ��½�ɫ����)
	 */
	public void updateAuthorize(User model, Integer[] ownRoleIds);

	/**
	 * �����Ȩ
	 */
	public void clearAuthorize(Integer userId);
	
}	
