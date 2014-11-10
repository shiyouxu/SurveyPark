package com.hitpoint.surveypark.struts2.action;

import java.awt.Font;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hitpoint.surveypark.model.Page;
import com.hitpoint.surveypark.model.statistics.OptionStatisticsModel;
import com.hitpoint.surveypark.model.statistics.QuestionStatisticsModel;
import com.hitpoint.surveypark.service.StatisticsService;

/**
 * ͼ�����action
 * @author Administrator
 */
@Controller
@Scope("prototype")
public class ChartOutputAction extends BaseAction<Page> {

	private static final long serialVersionUID = 898747543302372660L;

	private Integer qid;
	
	private int chartType;
	
	private InputStream is;
	
	public void setIs(InputStream is) throws Exception {
		this.is = this.getIs();
	}

	@Resource
	private StatisticsService ss;
	
	public Integer getPid() {
		return qid;
	}

	public void setPid(Integer qid) {
		this.qid = qid;
	}

	public int getChartType() {
		return chartType;
	}

	public void setChartType(int chartType) {
		this.chartType = chartType;
	}

	public String execute(){
		return "success";
	}
	
	/**
	 * ���ͼ��
	 * @throws Exception 
	 */
	public InputStream getIs() throws Exception{
		
		//ͳ������
		QuestionStatisticsModel qsm = ss.statistics(qid);
		//�����ͼ���ݼ�
		DefaultPieDataset ds = new DefaultPieDataset();
		for(OptionStatisticsModel osm: qsm.getOsms()){
			ds.setValue(osm.getOptionLabel(), osm.getCount());
		}
		//���ɱ�ͼ
		JFreeChart chart = ChartFactory.createPieChart(qsm.getQuestion().getTitle(), ds, true, false, false);
			
		chart.getTitle().setFont(new Font("����", Font.BOLD, 25));
		chart.getLegend().setItemFont(new Font("����", Font.PLAIN, 15));
		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setLabelFont(new Font("����", Font.PLAIN, 15));
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ChartUtilities.writeChartAsJPEG(baos, chart, 400, 300);
		return new ByteArrayInputStream(baos.toByteArray());
		
	}
}
