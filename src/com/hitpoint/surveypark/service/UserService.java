package com.hitpoint.surveypark.service;

import com.hitpoint.surveypark.model.User;

public interface UserService extends BaseService<User> {
	/**
	 * 判断email是否占用
	 */
	public boolean isRegisted(String email);

	
	/**
	 * 验证登陆信息
	 * @param email
	 * @param md5
	 * @return
	 */
	public User validateLoginInfo(String email, String md5);

	/**
	 * 更新用户授权(只能更新角色设置)
	 */
	public void updateAuthorize(User model, Integer[] ownRoleIds);

	/**
	 * 清除授权
	 */
	public void clearAuthorize(Integer userId);
	
}	
