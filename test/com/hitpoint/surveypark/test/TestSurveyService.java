package com.hitpoint.surveypark.test;

import static org.junit.Assert.*;

import java.sql.SQLException;


import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hitpoint.surveypark.model.User;
import com.hitpoint.surveypark.service.SurveyService;
import com.hitpoint.surveypark.service.UserService;
/*
 * ≤‚ ‘SurveyService
 */
public class TestSurveyService {
	private static SurveyService ss;
	
	@BeforeClass
	public static void iniSurveyService(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		ss = (SurveyService) ac.getBean("surveyService");
	}
	
	@Test
	public void newSurvey(){
		User u = new User();
		u.setId(3);
		ss.newSurvey(u);
	}
	
}