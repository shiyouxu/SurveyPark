package com.hitpoint.surveypark.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hitpoint.surveypark.dao.BaseDao;
import com.hitpoint.surveypark.model.security.Right;
import com.hitpoint.surveypark.service.RightService;
import com.hitpoint.surveypark.util.StringUtil;
import com.hitpoint.surveypark.util.ValidateUtil;

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
	
	/**
	 * 批量更新权限
	 */
	public void batchUpdateRights(List<Right> allRights) {
		String hql = "update Right r set r.rightName = ? where r.id = ?";
		if(ValidateUtil.isValid(allRights)){
			for(Right r : allRights){
				this.batchEntityByHQL(hql, r.getRightName(),r.getId());
			}
		}
	}
	
	/**
	 * 查询在指定范围内的权限
	 */
	public List<Right> findRightsInRange(Integer[] ownRightIds) {
		if(ValidateUtil.isValid(ownRightIds)){
			String hql = "from Right r where r.id in ("+StringUtil.arr2Str(ownRightIds)+")";
			return this.findEntityByHQL(hql);
		}
		return null;
	}

	public List<Right> findRightsNotInRange(Set<Right> rights) {
		if(ValidateUtil.isValid(rights)){
			return this.findAllEntities();
		}else{
			String hql = "from Right r where r.id not in ("+extractRightIds(rights)+")";
			return this.findEntityByHQL(hql);
		}
	}
	
	/**
	 * 抽取所有的right的id，形成字符串，以","分割
	 */
	private String extractRightIds(Set<Right> rights) {
		String temp = "";
		if(ValidateUtil.isValid(rights)){
			for(Right r: rights){
				temp = temp + r.getId() + ",";
			}
			return temp.substring(0,temp.length() - 1);
		}
		return temp;
	}

}
