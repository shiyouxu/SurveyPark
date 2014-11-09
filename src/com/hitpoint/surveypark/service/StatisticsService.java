package com.hitpoint.surveypark.service;

import com.hitpoint.surveypark.model.statistics.QuestionStatisticsModel;

public interface StatisticsService {
	public QuestionStatisticsModel statistics(Integer qid);
}
