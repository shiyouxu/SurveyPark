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
import com.hitpoint.surveypark.util.DataUtil;
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
	
	@Resource(name="answerDao")
	private BaseDao<Answer> answerDao;
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

	public void deleteQuestion(Integer qid) {
		//1��ɾ��answers
		String hql = "delete from Answer a where a.questionid = ?";
		answerDao.batchEntityByHQL(hql, qid);
		//2��delete question
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
		//hibernate��д�����У��������������ϵ�����
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
	 * �༭����
	 */
	public Question getQuestion(Integer qid) {
		return questionDao.getEntity(qid);
	}

	public void clearAnswers(Integer sid) {
		String hql = "delete from Answer a where a.surveyid = ?";
		answerDao.batchEntityByHQL(hql, sid);
	}

	public void toggleStatus(Integer sid) {
		Survey s = this.getSurvey(sid);
		String hql = "update Survey s set s.closed = ? where s.id = ?";
		surveyDao.batchEntityByHQL(hql,!s.isClosed(),sid);
	}

	public void updateLogoPhotoPath(Integer sid, String path) {
		String hql = "update Survey s set s.logoPhotoPath = ? where s.id = ?";
		surveyDao.batchEntityByHQL(hql, path,sid);
	}

	public List<Survey> getSurveyWithPages(User user) {
		String hql = "from Survey s where s.user.id = ?";
		List<Survey> list = surveyDao.findEntityByHQL(hql,user.getId());
		for (Survey s : list) {
			s.getPages().size();
		}
		return list;
	}

	public void moveOrCopyPage(Integer srcPid, Integer targPid, int pos) {
		Page srcPage = this.getPage(srcPid);
		Survey srcSurvey = srcPage.getSurvey();
		Page targPage = this.getPage(targPid);
		Survey targSurvey = targPage.getSurvey();
		//�ж��ƶ����Ǹ��ƣ���ͬ���ƶ�
		if(srcSurvey.getId().equals(targSurvey.getId())){
			changeOrderno(srcPage,targPage,pos);
		}
		//����
		else{
			//��ԭҳ���question����ǿ�г�ʼ���������޷����Ʋ���question����
			srcPage.getQuestions().size();
			//��ȸ���
			Page copyPage = (Page) DataUtil.deepluCopy(srcPage);
			//����ҳ���Ŀ��������
			copyPage.setSurvey(targSurvey);
			//����ҳ��
			pageDao.saveEntity(copyPage);
			for(Question q :copyPage.getQuestions()){
				questionDao.saveEntity(q);
			}
			changeOrderno(copyPage,targPage,pos);
		}
	}

	/**
	 * ����ҳ��
	 */
	public void changeOrderno(Page srcPage, Page targPage, int pos) {
		//֮ǰ
		if(pos == 0){
			//�ж�Ŀ��ҳ�Ƿ�����ҳ
			if(isFirstPage(targPage)){
				srcPage.setOrderno(targPage.getOrderno() - 0.01f);
			}
			else{
				//ȡ��Ŀ��ҳ��ǰһҳ
				Page prePage = getPrePage(targPage);
				srcPage.setOrderno((targPage.getOrderno()+prePage.getOrderno()) /2.0f);
			}
		}
		//֮��
		else{
			//�ж�Ŀ��ҳ�Ƿ���βҳ
			if(isLastPage(targPage)){
				srcPage.setOrderno(targPage.getOrderno() + 0.01f);
			}
			else{
				//ȡ��Ŀ��ҳ�ĺ�һҳ
				Page nextPage = getNextPage(targPage);
				srcPage.setOrderno((targPage.getOrderno()+nextPage.getOrderno()) /2.0f);
			}
		}
	}
	
	/**
	 * ��ѯָ��ҳ�����һҳ
	 */
	private Page getNextPage(Page targPage) {
		String hql = "from Page p where p.survey.id = ? and p.orderno > ? order by p.orderno asc";
		List<Page> list = pageDao.findEntityByHQL(hql, targPage.getSurvey().getId(),targPage.getOrderno());
		return list.get(0);
	}
	
	/**
	 * �ж�ָ��ҳ���Ƿ���ָ������βҳ
	 */
	private boolean isLastPage(Page targPage) {
		String hql = "select count(*) from Page p where p.survey.id = ? and p.orderno > ?";
		Long count = (Long)pageDao.uniqueResult(hql, targPage.getSurvey().getId(),targPage.getOrderno());
		//count = 0 ����βҳ
		return count == 0;
	}
	
	/**
	 * �ж�ָ��ҳ���Ƿ���ָ��������ҳ
	 */
	private boolean isFirstPage(Page targPage) {
		String hql = "select count(*) from Page p where p.survey.id = ? and p.orderno < ?";
		Long count = (Long)pageDao.uniqueResult(hql, targPage.getSurvey().getId(),targPage.getOrderno());
		//count = 0 ������ҳ
		return count == 0;
	}

	/**
	 * ��ѯָ��ҳ�����һҳ
	 */
	private Page getPrePage(Page targPage) {
		String hql = "from Page p where p.survey.id = ? and p.orderno < ? order by p.orderno desc";
		List<Page> list = pageDao.findEntityByHQL(hql, targPage.getSurvey().getId(),targPage.getOrderno());
		return list.get(0);
	}

	public List<Survey> findAllAvailableSurveys() {
		String hql = "from Survey s where s.closed = ?";
		return surveyDao.findEntityByHQL(hql, false);
	}
	
	/**
	 * ��õ�����ҳ
	 */
	public Page getFirstPage(Integer sid) {
		String hql = "from Page p where p.survey.id = ? order by p.orderno asc";
		List<Page> list = pageDao.findEntityByHQL(hql, sid);
		Page p = list.get(0);
		p.getQuestions().size();//��ʼ�����⼯��
		p.getSurvey().getTitle();//��ʼ���������
		return p;
	}
}










