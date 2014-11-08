package com.hitpoint.surveypark.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.hitpoint.surveypark.model.Page;
import com.hitpoint.surveypark.model.Question;
import com.hitpoint.surveypark.model.Survey;

public class App {

	/**深度复制
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		/*Survey s1 = new Survey();
		s1.setTitle("s1");
		
		Page p1 = new Page();
		p1.setTitle("p1");
		
		Question q1 = new Question();
		q1.setTitle("q1");
		
		Question q2 = new Question();
		q1.setTitle("q2");
		
		p1.setSurvey(s1);
		p1.getQuestions().add(q1);
		p1.getQuestions().add(q2);
		//----------------------------------
		//开始深度复制
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(p1);
		oos.close();
		baos.close();
		
		byte[] bytes = baos.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		ObjectInputStream ois = new ObjectInputStream(bais);
		Page copy = (Page)ois.readObject();
		ois.close();
		bais.close();
		
		System.out.println(copy);*/
		
		String key = "p12_1_2";
		System.out.println(Integer.parseInt(key.substring(1,key.indexOf("_"))));
		
	}

}
