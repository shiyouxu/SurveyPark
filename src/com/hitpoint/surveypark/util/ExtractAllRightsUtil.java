package com.hitpoint.surveypark.util;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hitpoint.surveypark.service.RightService;

/**
 * 提取所有权限的工具类
 * @author leo.zhang
 *
 */
public class ExtractAllRightsUtil {
	public static void main(String[] args) throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		RightService rs = (RightService) ac.getBean("rightService");
		ClassLoader classLoader = ExtractAllRightsUtil.class.getClassLoader();
		URL url = classLoader.getResource("com/hitpoint/surveypark/struts2/action");
		File dir = new File(url.toURI());
		File[] files = dir.listFiles();
		String fname = "";
		for(File f:files){
			fname = f.getName();
			if(fname.endsWith(".class") && !fname.equals("BaseAction.class")){
				processAction(fname,rs);
			}
		}
	}

	/**
	 * 处理action类，捕获所有url地址,形成权限
	 */
	private static void processAction(String fname,RightService rs) {
		try {
			String packageName = "com.hitpoint.surveypark.struts2.action";
			String simpleClassName = fname.substring(0,fname.indexOf(".class"));
			String className = packageName + "." +simpleClassName;
			//得到具体类
			Class clazz = Class.forName(className);
			Method[] methods = clazz.getDeclaredMethods();
			Class returnType = null;
			String mname = null;
			Class[] paramType = null;
			String url = null;
			for(Method m : methods){
				returnType = m.getReturnType();
				mname = m.getName();
				paramType = m.getParameterTypes();
				if(returnType == String.class 
						&& !ValidateUtil.isValid(paramType)
						&& Modifier.isPublic(m.getModifiers())
						&& !mname.startsWith("get")){
					if(mname.equals("execute")){
						url = "/" + simpleClassName;
					}else{
						url = "/" + simpleClassName + "_" + mname;
					}
					rs.appendRightByURL(url);
				}
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
