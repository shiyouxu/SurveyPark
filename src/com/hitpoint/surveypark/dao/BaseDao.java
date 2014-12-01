package com.hitpoint.surveypark.dao;

import java.util.List;
/**
 * BasaDao�ӿ�
 * @author Administrator
 *
 * @param <T>
 */
public interface BaseDao<T> {
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
	//��ֵ����,ȷ����ѯ�������ֻ��һ����¼
	public Object uniqueResult(String hql,Object...objects);
}
