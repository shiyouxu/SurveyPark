package com.hitpoint.surveypark.struts2.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hitpoint.surveypark.model.User;
import com.hitpoint.surveypark.model.security.Role;
import com.hitpoint.surveypark.service.RoleService;
import com.hitpoint.surveypark.service.UserService;

/**
 * 用户授权action
 */
@Controller
@Scope("prototype")
public class UserAuthorizeAction extends BaseAction<User> {

	private static final long serialVersionUID = -2768868235822280263L;
	
	private List<User> allUsers;

	private Integer userId;
	
	//用户没有的角色集合
	private List<Role> noOwnRoles ;
	
	@Resource
	private RoleService roleService;
	
	public List<Role> getNoOwnRoles() {
		return noOwnRoles;
	}

	public void setNoOwnRoles(List<Role> noOwnRoles) {
		this.noOwnRoles = noOwnRoles;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Resource
	private UserService userSerivce;
	
	public List<User> getAllUsers() {
		return allUsers;
	}

	public void setAllUsers(List<User> allUsers) {
		this.allUsers = allUsers;
	}
	
	/**
	 * 查询所有用户
	 */
	public String findAllUsers(){
		this.allUsers = userSerivce.findAllEntities();
		return "userAuthorizeListPage";
	}
	
	/**
	 * 修改授权
	 */
	public String editAuthorize(){
		this.model = userSerivce.getEntity(userId);
		this.noOwnRoles = roleService.findRolesNotInRange(model.getRoles());
		return "editAuthorizePage";
	}
}
