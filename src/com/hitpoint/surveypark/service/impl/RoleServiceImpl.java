package com.hitpoint.surveypark.service.impl;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hitpoint.surveypark.dao.BaseDao;
import com.hitpoint.surveypark.model.security.Right;
import com.hitpoint.surveypark.model.security.Role;
import com.hitpoint.surveypark.service.RightService;
import com.hitpoint.surveypark.service.RoleService;
import com.hitpoint.surveypark.util.DataUtil;
import com.hitpoint.surveypark.util.StringUtil;
import com.hitpoint.surveypark.util.ValidateUtil;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

	@Resource(name="roleDao")
	public void setDao(BaseDao<Role> dao){
		super.setDao(dao);
	}
	
	@Resource
	private RightService rightService;
	
	/**
	 * 保存/更新角色
	 */
	public void saveOrUpdateRole(Role model, Integer[] ownRightIds) {
		//没有给角色授予任何权限
		if(ValidateUtil.isValid(ownRightIds)){
			model.getRights().clear();
		}else{
			List<Right> rights = rightService.findRightsInRange(ownRightIds);
			model.setRights(new HashSet<Right>(rights));
		}
		this.saveOrUpdateEntity(model);
	}
	
	/**
	 * 查询用户没有的角色集合
	 */
	public List<Role> findRolesNotInRange(Set<Role> roles) {
		if(!ValidateUtil.isValid(roles)){
			return this.findAllEntities();
		}else{
			String hql = "from Role r where r.id not in ("+DataUtil.extractIds(roles)+")";
			return this.findEntityByHQL(hql);
		}
	}

	/**
	 * 查询在指定范围中的角色集合
	 */
	public List<Role> findRolesInRange(Integer[] ids) {
		if(ValidateUtil.isValid(ids)){
			String hql = "from Role r where r.id in ("+StringUtil.arr2Str(ids)+")";
			return this.findEntityByHQL(hql);
		}
		return null;
	}

}
