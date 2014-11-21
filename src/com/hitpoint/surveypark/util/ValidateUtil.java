package com.hitpoint.surveypark.util;

import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hitpoint.surveypark.model.User;
import com.hitpoint.surveypark.model.security.Right;
import com.hitpoint.surveypark.struts2.UserAware;
import com.hitpoint.surveypark.struts2.action.BaseAction;

/**
 * 校验工具类
 * @author leo.zhang
 *
 */
public class ValidateUtil {
	/**
	 * 判断字符串有效性
	 * @param src
	 * @return
	 */
	public static boolean isValid(String src){
		if(src ==null ||"".equals(src.trim())){
			return false;
		}
		return true;
	}
	
	/**
	 * 判断集合的有效性
	 */
	public static boolean isValid(Collection c){
		if(c==null || c.isEmpty()){
			return false;
		}
		return true;
	}
	
	/**
	 * 判断数组的有效性
	 */
	public static boolean isValid(Object[] arr){
		if(arr == null || arr.length == 0){
			return false;
		}
		return true;
	}
	
	/**
	 * 判断是否有权限
	 */
	public static boolean hasRight(String namespace,String actionName,HttpServletRequest req,BaseAction action){
		if(!ValidateUtil.isValid(namespace) || "/".equals(namespace)){
			namespace = "";
		}
		//将超链接的参数部分滤掉
		if(actionName.contains("?")){
			actionName = actionName.substring(0,actionName.indexOf("?"));
		}
		String url = namespace + "/" +actionName;
		HttpSession session = req.getSession();
		ServletContext sc = session.getServletContext();
		Map<String,Right> map = (Map<String, Right>) sc.getAttribute("all_rights_map");
		Right r = map.get(url);
		//公共资源
		if(r == null || r.isCommon()){
			return true;
		}else{
			User user = (User) session.getAttribute("user");
			//是否登陆
			if(user == null){
				return false;
			}else{
				//userAware处理
				if( action != null && action instanceof UserAware){
					((UserAware)action).setUser(user);
				}
				
				//是否超级管理员
				if(user.isSuperAdmin()){
					return true;
				}else{
					//是否有权限
					if(user.hasRight(r)){
						return true;
					}else{
						return false;
					}
				}
			}
			
		}
	}
}
