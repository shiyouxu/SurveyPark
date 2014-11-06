package com.hitpoint.surveypark.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hitpoint.surveypark.model.Survey;
import com.hitpoint.surveypark.model.User;
import com.hitpoint.surveypark.service.SurveyService;
import com.hitpoint.surveypark.service.UserService;
/*
 * ²âÊÔSurveyService
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
		u.setId(1);
		ss.newSurvey(u);
	}
	
	@Test
	public void TestmySurveys(){
		//User user = (User) sessionMap.get("user");
		User user = new User();
		user.setId(1);
		List<Survey> mySurveys = new ArrayList<Survey>();
		mySurveys = ss.findMySurveys(user);
		for (Survey survey : mySurveys) {
			System.out.println(survey.getId());
		}
	}
	
	//ÇÐ»»×´Ì¬
	@Test
	public void toggleStatus(){
		
	}
	
	
	//²éÑ¯µ÷²é
	@Test
	public void getSurvey(){
		Survey s = ss.getSurvey(1);
	}
	
}