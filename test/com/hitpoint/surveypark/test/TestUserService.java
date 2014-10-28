package com.hitpoint.surveypark.test;

import static org.junit.Assert.*;

import java.sql.SQLException;


import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hitpoint.surveypark.model.User;
import com.hitpoint.surveypark.service.UserService;
/*
 * ≤‚ ‘UserService
 */
public class TestUserService {
	private static UserService us;
	
	@BeforeClass
	public static void iniUserService(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		us = (UserService) ac.getBean("userService");
	}
	
	/**
	 * ≤Â»Î”√ªß
	 */
	@Test
	public void insertUser() throws SQLException{
		User u =new User();
		u.setEmail("leo.zhang@hitpointcloud.com");
		u.setPassword("223456");
		u.setNickname("leo");
		us.saveEntity(u);
	}

}
