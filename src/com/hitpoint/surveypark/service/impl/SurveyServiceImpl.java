package com.hitpoint.surveypark.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hitpoint.surveypark.dao.BaseDao;
import com.hitpoint.surveypark.model.Page;
import com.hitpoint.surveypark.model.Question;
import com.hitpoint.surveypark.model.Survey;
import com.hitpoint.surveypark.model.User;
import com.hitpoint.surveypark.service.SurveyService;
/**
 * SurveyServiceʵ��
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
	
	/**
	 * ��ѯ���鼯��
	 */
	public List<Survey> findMySurveys(User user) {
		String hql = "from Survey s where s.user.id = ?";
		return surveyDao.findEntityByHQL(hql,user.getId());
	}

	/**
	 * �½�����
	 */
	public Survey newSurvey(User user) {
		Survey s = new Survey();
		Page p = new Page();
		
		//���ù�����ϵ
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
		//ǿ�г�ʼ��pages��questions����
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

}
