package com.hitpoint.surveypark.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.util.Set;

import com.hitpoint.surveypark.model.BaseEntity;
import com.hitpoint.surveypark.model.security.Role;

public class DataUtil {
	public static String md5(String src){
		try {
			StringBuffer buffer = new StringBuffer();
			char[] chars = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
			byte[] bytes = src.getBytes();
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] targ = md.digest(bytes);
			for (byte b : targ) {
				buffer.append(chars[(b>>4)& 0x0F]);
				buffer.append(chars[b & 0x0F]);
			}
			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 深度复制，复制整个对象 图
	 */
	public static Serializable deepluCopy(Serializable src){
		try{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(src);
			oos.close();
			baos.close();
			
			byte[] bytes = baos.toByteArray();
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			Serializable copy = (Serializable)ois.readObject();
			ois.close();
			bais.close();
			return copy;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 抽取实体的id,形成字符串
	 */
	public static String extractIds(Set<? extends BaseEntity> entities){
		String temp = "";
		if(ValidateUtil.isValid(entities)){
			for(BaseEntity e: entities){
				temp = temp + e.getId() + ",";
			}
			return temp.substring(0,temp.length()-1);
		}
		return temp;
	}
}
