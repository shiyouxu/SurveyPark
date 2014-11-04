package com.hitpoint.surveypark.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hitpoint.surveypark.dao.BaseDao;
import com.hitpoint.surveypark.model.Answer;
import com.hitpoint.surveypark.model.Page;
import com.hitpoint.surveypark.model.Question;
import com.hitpoint.surveypark.model.Survey;
import com.hitpoint.surveypark.model.User;
import com.hitpoint.surveypark.service.SurveyService;
/**
 * SurveyService实现
 * @author leo.zhang
 *
 */
@Service("surveyService")
public class SurveyServiceImpl implements SurveyService {
	
	@Resource(name="surveyDao")
	private BaseDao<Survey> surveyDao;
	
	@Resource(name="pageDao")
	private BaseDao<Page> pageDao;
	
	@Resource(name="questionDao")
	private BaseDao<Question> questionDao;
	
	@Resource(name="answerDao")
	private BaseDao<Answer> answerDao;
	/**
	 * 查询调查集合
	 */
	public List<Survey> findMySurveys(User user) {
		String hql = "from Survey s where s.user.id = ?";
		return surveyDao.findEntityByHQL(hql,user.getId());
	}

	/**
	 * 新建调查
	 */
	public Survey newSurvey(User user) {
		Survey s = new Survey();
		Page p = new Page();
		
		//设置关联关系
		s.setUser(user);
		p.setSurvey(s);
		s.getPages().add(p);
		
		surveyDao.saveEntity(s);
		pageDao.saveEntity(p);
		return s;
	}

	public Survey getSurvey(Integer sid) {
		return surveyDao.getEntity(sid);
	}

	public Survey getSurveyWithChildren(Integer sid) {
		//Survey s = surveyDao.getEntity(sid);
		Survey s = this.getSurvey(sid);
		//强行初始化pages和questions集合
		for(Page p:s.getPages()){
			p.getQuestions().size();
		}
		return s;
	}

	public void updateSurvey(Survey model) {
		surveyDao.updateEntity(model);
	}

	public void savaOrUpdatePage(Page model) {
		pageDao.saveOrUpdateEntity(model);
	}

	public Page getPage(Integer pid) {
		return pageDao.getEntity(pid);
	}

	public void savaOrUpdateQuestion(Question model) {
		questionDao.saveOrUpdateEntity(model);
	}

	public void deleteQuestion(Integer qid) {
		//1、删除answers
		String hql = "delete from Answer a where a.questionid = ?";
		answerDao.batchEntityByHQL(hql, qid);
		//2、delete question
		hql = "delete from Question q where q.id = ?";
		questionDao.batchEntityByHQL(hql, qid);
	}

	public void deletePage(Integer pid) {
		//delete answer
		String hql = "delete from Answer a where a.questionid in (select q.id from Question q where q.page.id = ?)";
		answerDao.batchEntityByHQL(hql, pid);
		//delete questions
		hql = "delete from Question q where q.page.id = ?";
		answerDao.batchEntityByHQL(hql, pid);
		//delete pages
		hql = "delete from Page p where p.id = ?";
		answerDao.batchEntityByHQL(hql, pid);
	}

	public void deleteSurvey(Integer sid) {
		//delete answer 
		String hql = "delete from Answer a where a.surveyid = ?";
		answerDao.batchEntityByHQL(hql, sid);
		//delete question
		//hibernate在写操作中，不允许两级以上的连接
		//hql = "delete from Question q where q.page.survey.id = ?";
		hql = "delete from Question q where q.page.id in(select p.id from Page p where p.survey.id = ?)";
		answerDao.batchEntityByHQL(hql, sid);
		//delete pages
		hql = "delete from Page p where p.survey.id = ?";
		answerDao.batchEntityByHQL(hql, sid);
		//delete survey
		hql = "delete from Survey s where s.id = ?";
		answerDao.batchEntityByHQL(hql, sid);
	}
	
	/**
	 * 编辑问题
	 */
	public Question getQuestion(Integer qid) {
		return questionDao.getEntity(qid);
	}
}










