package com.hitpoint.surveypark.service.impl;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hitpoint.surveypark.dao.BaseDao;
import com.hitpoint.surveypark.model.User;
import com.hitpoint.surveypark.model.security.Role;
import com.hitpoint.surveypark.service.RoleService;
import com.hitpoint.surveypark.service.UserService;
import com.hitpoint.surveypark.util.ValidateUtil;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
	
	@Resource
	private RoleService roleService;
	
	@Resource(name="userDao")
	public void setDao(BaseDao<User> dao) {
		super.setDao(dao);
	}

	public boolean isRegisted(String email) {
		String hql = "from User u where u.email = ?";
		List<User> list = this.findEntityByHQL(hql, email);
		return ValidateUtil.isValid(list);
	}

	public User validateLoginInfo(String email, String md5) {
		String hql = "from User u where u.email = ? and u.password = ?";
		List<User> list = this.findEntityByHQL(hql, email,md5);
		return ValidateUtil.isValid(list)?list.get(0):null;
	}

	
	public void updateAuthorize(User model, Integer[] ids) {
		//查询新对象，不可对原有对象进行操作
		User newUser = this.getEntity(model.getId());
		if(!ValidateUtil.isValid(ids)){
			newUser.getRoles().clear();
		}else{
			List<Role> roles = roleService.findRolesInRange(ids);
			newUser.setRoles(new HashSet<Role>(roles));
		}
	}

	public void clearAuthorize(Integer userId) {
		this.getEntity(userId).getRoles().clear();
	}
	
	
}
