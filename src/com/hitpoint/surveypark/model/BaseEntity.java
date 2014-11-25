package com.hitpoint.surveypark.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public abstract class BaseEntity implements Serializable {
	private static final long serialVersionUID = 8899160647957785251L;

	public abstract Integer getId() ;

	public abstract void setId(Integer id) ;
	
	public String toString() {
		try {
			StringBuffer buffer = new StringBuffer();
			Class clazz = this.getClass();
			String simpleName = clazz.getSimpleName();
			buffer.append(simpleName);
			buffer.append("{");
			
			Field[] fields = clazz.getDeclaredFields();
			Class fieldType = null;
			String fieldName = null;
			Object fieldValue = null;
			for(Field f: fields){
				fieldType = f.getType();
				fieldName = f.getName();
				f.setAccessible(true);
				fieldValue = f.get(this);
				//是否是基本数据类型
				if((fieldType.isPrimitive()  
						||fieldType == Integer.class || fieldType == Short.class
						||fieldType == Boolean.class || fieldType == Long.class
						||fieldType == Character.class || fieldType == Double.class
						||fieldType == Float.class || fieldType == String.class)
						&& !Modifier.isStatic(f.getModifiers())){
					buffer.append(fieldName);
					buffer.append(":");
					buffer.append(fieldValue);
					buffer.append(",");
				}
			}
			buffer.append("}");
			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.toString();
	}
}
