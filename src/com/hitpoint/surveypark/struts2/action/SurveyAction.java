package com.hitpoint.surveypark.struts2.action;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hitpoint.surveypark.model.Survey;
import com.hitpoint.surveypark.model.User;
import com.hitpoint.surveypark.service.SurveyService;
import com.hitpoint.surveypark.struts2.UserAware;
import com.hitpoint.surveypark.util.ValidateUtil;
@Controller
@Scope("prototype")
public class SurveyAction extends BaseAction<Survey> implements UserAware,ServletContextAware {

	private static final long serialVersionUID = -2508803636993650070L;
	
	//注入surveyService
	@Resource
	private SurveyService surveyService;
	
	//调查集合
	private List<Survey> mySurveys;
	
	//接收sessionMap
	//private Map<String, Object> sessionMap;
	
	//接收user对象
	private User user;
	
	//接收sid参数
	private Integer sid;
	
	//动态错误页指定
	private String inputPage;
	
	public String getInputPage() {
		return inputPage;
	}

	public void setInputPage(String inputPage) {
		this.inputPage = inputPage;
	}

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
	 * 查询我的调查列表
	 */
	public String mySurveys(){
		//User user = (User) sessionMap.get("user");
		//System.out.println(user.getId());
		this.mySurveys = surveyService.findMySurveys(user);
		return "mySurveyListPage";
	}
	
	/**
	 * 新建调查
	 */
	public String newSurvey(){
		//User user = (User) sessionMap.get("user");
		this.model = surveyService.newSurvey(user);
		return "designSurveyPage";
	}
	
	/**
	 * 设计调查
	 */
	public String designSurvey(){
		this.model = surveyService.getSurveyWithChildren(sid);
		return "designSurveyPage";
	}
	
	/**
	 * 编辑调查
	 */
	public String editSurvey(){
		this.model = surveyService.getSurvey(sid);
		return "editSurveyPage";
	}
	
	/**
	 * 更新调查
	 */
	public String updateSurvey(){
		this.sid = model.getId();
		//保持关联关系
		model.setUser(user);
		System.out.println(model.getNextText());
		surveyService.updateSurvey(model);
		return "designSurveyAction";
	}
	
	public void prepareUpdateSurvey(){
		inputPage = "/editSurvey.jsp";
	}
		
	/**
	 * 该方法只在designSurvey之前运行
	 */
//	public void prepareDesignSurvey(){
//		this.model = surveyService.getSurveyWithChildren(sid);
//	}
	
	//注入user对象
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * 删除调查
	 */
	public String deleteSurvey(){
		surveyService.deleteSurvey(sid);
		return "findMySurveysAction";
	}
	
	/**
	 * 清除调查的答案
	 */
	public String clearAnswers(){
		surveyService.clearAnswers(sid);
		return "findMySurveysAction";
	}
	
	/**
	 * 切换状态
	 */
	public String toggleStatus(){
		surveyService.toggleStatus(sid);
		return "findMySurveysAction";
	}
	
	/**
	 * 到达增加logo的页面
	 */
	public String toAddLogoPage(){
		return "addLogoPage";
	}
	
	//上传文件
	private File logoPhoto;
	public File getLogoPhoto() {
		return logoPhoto;
	}

	public void setLogoPhoto(File logoPhoto) {
		this.logoPhoto = logoPhoto;
	}

	//文件名称
	private String logoPhotoFileName;
	//接收ServletContext对象
	private ServletContext sc;
	

	public String getLogoPhotoFileName() {
		return logoPhotoFileName;
	}

	public void setLogoPhotoFileName(String logoPhotoFileName) {
		this.logoPhotoFileName = logoPhotoFileName;
	}

	public void prepareDoAddLogo(){
		inputPage = "/addLogo.jsp";
	}
		
	/**
	 * 实现logo上传
	 */
	public String doAddLogo(){
		if(ValidateUtil.isValid(logoPhotoFileName)){
			//1、实现上传
			String dir = sc.getRealPath("/upload");
			//upload文件夹真实路径
			String ext = logoPhotoFileName.substring(logoPhotoFileName.lastIndexOf("."));
			//纳秒时间作为文件名
			long l = System.nanoTime();
			File newFile = new File(dir,l+ext);
			//文件另存为
			logoPhoto.renameTo(newFile);
			//2、更新路径
			surveyService.updateLogoPhotoPath(sid,"/upload/"+l+ext);
		}
		return "designSurveyAction";
	}
	
	
	/**
	 * logo图片是否存在
	 */
	public boolean photoExist(){
		String path = model.getLogoPhotoPath();
		if(ValidateUtil.isValid(path)){
			String absPath = sc.getRealPath(path);
			File file = new File(absPath);
			return file.exists();
		}
		
		return false;
	}
	
	/**
	 * 分析调查
	 */
	public String analyzeSurvey(){
		this.model = surveyService.getSurveyWithChildren(sid);
		return "analyzeSurveyListPage";
	}
	
	//注入ServletContext对象
	public void setServletContext(ServletContext arg0) {
		this.sc = arg0;
	}
}
