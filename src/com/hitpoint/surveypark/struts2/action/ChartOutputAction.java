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

	private String qid;
	
	public String getQid() {
		return qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	private int chartType;
	
	@Resource
	private StatisticsService ss;
	

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
	public JFreeChart getChart() throws Exception{
		
		//ͳ������
		System.out.println(qid+"-----------------------------------");
		QuestionStatisticsModel qsm = ss.statistics(Integer.parseInt(qid));
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
		
		return chart;
		
	}
}
