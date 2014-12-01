package com.hitpoint.surveypark.service;

import java.util.List;
/**
 * ������service�ӿ�
 * @author leo.zhang
 *
 * @param <T>
 */
public interface BaseService<T> {
	//д����
	public void saveEntity(T t);
	public void saveOrUpdateEntity(T t);
	public void updateEntity(T t);
	public void deleteEntity(T t);
	public void batchEntityByHQL(String hql,Object...objects);
	//ִ��ԭ����SQL���
	public void executeSQL(String sql,Object...objects);
	
	//������
	public T loadEntity(Integer id);
	public T getEntity(Integer id);
	public List<T> findEntityByHQL(String hql,Object...objects);
	//��ֵ������ȷ����ѯ���ֻ��һ����¼
	public Object uniqueResult(String hql,Object...objects);
	//��ѯ����ʵ��
	public List<T> findAllEntities();
}
