package com.hitpoint.surveypark.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hitpoint.surveypark.dao.BaseDao;
import com.hitpoint.surveypark.model.Page;
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

}
