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
 * У�鹤����
 * @author leo.zhang
 *
 */
public class ValidateUtil {
	/**
	 * �ж��ַ�����Ч��
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
	 * �жϼ��ϵ���Ч��
	 */
	public static boolean isValid(Collection c){
		if(c==null || c.isEmpty()){
			return false;
		}
		return true;
	}
	
	/**
	 * �ж��������Ч��
	 */
	public static boolean isValid(Object[] arr){
		if(arr == null || arr.length == 0){
			return false;
		}
		return true;
	}
	
	/**
	 * �ж��Ƿ���Ȩ��
	 */
	public static boolean hasRight(String namespace,String actionName,HttpServletRequest req,BaseAction action){
		if(!ValidateUtil.isValid(namespace) || "/".equals(namespace)){
			namespace = "";
		}
		//�������ӵĲ��������˵�
		if(actionName.contains("?")){
			actionName = actionName.substring(0,actionName.indexOf("?"));
		}
		String url = namespace + "/" +actionName;
		HttpSession session = req.getSession();
		ServletContext sc = session.getServletContext();
		Map<String,Right> map = (Map<String, Right>) sc.getAttribute("all_rights_map");
		Right r = map.get(url);
		//������Դ
		if(r == null || r.isCommon()){
			return true;
		}else{
			User user = (User) session.getAttribute("user");
			//�Ƿ��½
			if(user == null){
				return false;
			}else{
				//userAware����
				if( action != null && action instanceof UserAware){
					((UserAware)action).setUser(user);
				}
				
				//�Ƿ񳬼�����Ա
				if(user.isSuperAdmin()){
					return true;
				}else{
					//�Ƿ���Ȩ��
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
