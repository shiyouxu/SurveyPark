package com.hitpoint.surveypark.struts2.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hitpoint.surveypark.model.Answer;
import com.hitpoint.surveypark.model.Question;
import com.hitpoint.surveypark.model.Survey;
import com.hitpoint.surveypark.service.SurveyService;

/**
 * �ռ�����
 */
@Controller
@Scope("prototype")
public class CollectionSurveyAction extends BaseAction<Survey> {

	private static final long serialVersionUID = -247824069074541436L;
	
	private Integer sid;
	
	@Resource
	private SurveyService surveyService;
	
	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}
	
	public String execute() throws Exception {
		
		return SUCCESS;
	}
	
	public InputStream getIs(){
		try {
			//�������id������������֮���ӳ��
			Map<Integer,Integer> qidIndexMap = new HashMap<Integer, Integer>();
			List<Question> list = surveyService.getQuestions(sid);
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("�ռ�����");
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = null;
			Question q =null;
			//�������
			for(int i=0;i<list.size();i++){
				q = list.get(i);
				cell = row.createCell(i);
				cell.setCellValue(q.getTitle());
				sheet.setColumnWidth(i, 6000);
				qidIndexMap.put(q.getId(), i);
			}
			HSSFCellStyle style = wb.createCellStyle();
			style.setWrapText(true);
			
			//�����
			List<Answer> answers = surveyService.getAnswers(sid);
			String oldUuid = "";
			String newUuid = "";
			int rowIndex = 0;
			for(Answer a :answers){
				newUuid = a.getUuid();
				if(!newUuid.equals(oldUuid)){
					rowIndex ++;
					oldUuid = newUuid;
					row = sheet.createRow(rowIndex);
				}
				cell = row.createCell(qidIndexMap.get(a.getQuestionid()));
				cell.setCellValue(a.getAnswerIds());
				cell.setCellStyle(style);
			}
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			wb.write(baos);
			return new ByteArrayInputStream(baos.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
