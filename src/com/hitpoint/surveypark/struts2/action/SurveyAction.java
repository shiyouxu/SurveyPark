package com.hitpoint.surveypark.struts2.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hitpoint.surveypark.model.Page;
import com.hitpoint.surveypark.model.Question;
import com.hitpoint.surveypark.model.Survey;
import com.hitpoint.surveypark.model.User;
import com.hitpoint.surveypark.service.SurveyService;
import com.hitpoint.surveypark.struts2.UserAware;
import com.hitpoint.surveypark.util.ValidateUtil;
@Controller
@Scope("prototype")
public class SurveyAction extends BaseAction<Survey> implements UserAware,ServletContextAware {

	private static final long serialVersionUID = -2508803636993650070L;
	
	//ע��surveyService
	@Resource
	private SurveyService surveyService;
	
	//���鼯��
	private List<Survey> mySurveys;
	
	//����sessionMap
	//private Map<String, Object> sessionMap;
	
	//����user����
	private User user;
	
	//����sid����
	private Integer sid;
	
	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public List<Survey> getMySurveys() {
		return mySurveys;
	}

	public void setMySurveys(List<Survey> mySurveys) {
		this.mySurveys = mySurveys;
	}

	/**
	 * ��ѯ�ҵĵ����б�
	 */
	public String mySurveys(){
		//User user = (User) sessionMap.get("user");
		//System.out.println(user.getId());
		this.mySurveys = surveyService.findMySurveys(user);
		return "mySurveyListPage";
	}
	
	/**
	 * �½�����
	 */
	public String newSurvey(){
		//User user = (User) sessionMap.get("user");
		this.model = surveyService.newSurvey(user);
		return "designSurveyPage";
	}
	
	/**
	 * ��Ƶ���
	 */
	public String designSurvey(){
		this.model = surveyService.getSurveyWithChildren(sid);
		Set<Page> pages = model.getPages();
		for (Page page : pages) {
			System.out.println(page.getTitle()+"----------------------------------");
			Set<Question> questions = page.getQuestions();
			for (Question question : questions) {
				System.out.println(question.getTitle()+"============================");
			}
		}
		return "designSurveyPage";
	}
	
	/**
	 * �༭����
	 */
	public String editSurvey(){
		this.model = surveyService.getSurvey(sid);
		return "editSurveyPage";
	}
	
	/**
	 * ���µ���
	 */
	public String updateSurvey(){
		this.sid = model.getId();
		//���ֹ�����ϵ
		model.setUser(user);
		System.out.println(model.getNextText());
		surveyService.updateSurvey(model);
		return "designSurveyAction";
	}
	
	/**
	 * �÷���ֻ��designSurvey֮ǰ����
	 */
//	public void prepareDesignSurvey(){
//		this.model = surveyService.getSurveyWithChildren(sid);
//	}
	
	//ע��user����
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * ɾ������
	 */
	public String deleteSurvey(){
		surveyService.deleteSurvey(sid);
		return "findMySurveysAction";
	}
	
	/**
	 * �������Ĵ�
	 */
	public String clearAnswers(){
		surveyService.clearAnswers(sid);
		return "findMySurveysAction";
	}
	
	/**
	 * �л�״̬
	 */
	public String toggleStatus(){
		surveyService.toggleStatus(sid);
		return "findMySurveysAction";
	}
	
	/**
	 * ��������logo��ҳ��
	 */
	public String toAddLogoPage(){
		return "addLogoPage";
	}
	
	//�ϴ��ļ�
	private File logoPhoto;
	//�ļ�����
	private String logoPhotoFileName;
	//����ServletContext����
	private ServletContext sc;
	
	public File getLogoFile() {
		return logoPhoto;
	}

	public void setLogoFile(File logoFile) {
		this.logoPhoto = logoFile;
	}

	public String getLogoPhotoFileName() {
		return logoPhotoFileName;
	}

	public void setLogoPhotoFileName(String logoPhotoFileName) {
		this.logoPhotoFileName = logoPhotoFileName;
	}

	/**
	 * ʵ��logo�ϴ�
	 * @throws FileNotFoundException 
	 */
	public String doAddLogo() throws FileNotFoundException{
		System.out.println(sid+"---------------------------");
		if(ValidateUtil.isValid(logoPhotoFileName)){
			//1��ʵ���ϴ�
			String dir = sc.getRealPath("/upload");
			//upload�ļ�����ʵ·��
			String ext = logoPhotoFileName.substring(logoPhotoFileName.lastIndexOf("."));
			//����ʱ����Ϊ�ļ���
			long l = System.nanoTime();
			File newFile = new File(dir,l+ext);
			//�ļ����Ϊ
			logoPhoto.renameTo(newFile);
			//2������·��
			surveyService.updateLogoPhotoPath(sid,"/upload/"+l+ext);
		}
		return "designSurveyAction";
	}
	
	//ע��ServletContext����
	public void setServletContext(ServletContext arg0) {
		this.sc = arg0;
	}
	
}
