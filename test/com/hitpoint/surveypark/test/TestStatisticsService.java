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
import com.hitpoint.surveypark.service.StatisticsService;
import com.hitpoint.surveypark.service.SurveyService;
import com.hitpoint.surveypark.service.UserService;
/*
 * ≤‚ ‘SurveyService
 */
public class TestStatisticsService {
	private static StatisticsService ss;
	
	@BeforeClass
	public static void iniSurveyService(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		ss = (StatisticsService) ac.getBean("statisticsService");
	}
	
	@Test
	public void statistics() {
		ss.statistics(5);
	}
}
//org.hibernate.QueryException: could not resolve property: answer of: com.hitpoint.surveypark.model.Answer [select count(*) from com.hitpoint.surveypark.model.Answer a where a.questionid = ? and concat(',',a.answer.id,',') like ?]
