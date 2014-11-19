package com.hitpoint.surveypark.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hitpoint.surveypark.dao.BaseDao;
import com.hitpoint.surveypark.model.security.Right;
import com.hitpoint.surveypark.service.RightService;

@Service("rightService")
public class RightSerivceImpl extends BaseServiceImpl<Right> implements RightService {
	
	@Resource(name="rightDao")
	public void setDao(BaseDao<Right> dao){
		super.setDao(dao);
	}

	public void saveOrUpdateRight(Right r) {
		if(r.getId() == null){
			int pos = 0;
			long code =1L;
			String hql = "select max(r.rightPos),max(r.rightCode) from Right r " +
					"where r.rightPos = (select max(rr.rightPos) from Right rr)";
			Object[] arr = (Object[]) this.uniqueResult(hql);
			Integer topPos = (Integer) arr[0];
			Long topCode = (Long) arr[1];
			//没有权限
			if(topPos == null){
				pos = 0;
				code = 1L;
			}else{
				//权限码是否达到最大值
				if(topCode >= (1L << 60)){
					pos = topPos +1 ;
					code = 1L;
				}else{
					topPos = pos;
					code = topCode << 1;
				}
			}
			r.setRightPos(pos);
			r.setRightCode(code);
		}
		this.saveOrUpdateEntity(r);
	}
	
	/**
	 * 按照url追加权限
	 */
	public void appendRightByURL(String url) {
		String hql = "select count(*) from Right r where r.rightUrl = ?";
		Long count = (Long) this.uniqueResult(hql, url);
		if(count == 0){
			Right r= new Right();
			r.setRightUrl(url);
			this.saveOrUpdateRight(r);
		}
		
	}
}
