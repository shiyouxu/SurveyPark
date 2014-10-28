package com.hitpoint.surveypark.service;

import com.hitpoint.surveypark.model.User;

public interface UserService extends BaseService<User> {
	/**
	 * ≈–∂œemail «∑Ò’º”√
	 */
	public boolean isRegisted(String email);
	
}	
