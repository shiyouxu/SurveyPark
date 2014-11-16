package com.hitpoint.surveypark.struts2.action;

import java.awt.Font;

import javax.annotation.Resource;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;
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

	/*ƽ���ͼ*/
	private static final int CHARTTYPE_PIE_2D = 0;
	/*�����ͼ*/
	private static final int CHARTTYPE_PIE_3D = 1;
	/*ˮƽƽ����״ͼ*/
	private static final int CHARTTYPE_BAR_2D_H = 2;
	/*��ֱƽ����״ͼ*/
	private static final int CHARTTYPE_BAR_2D_V = 3;
	/*ˮƽ������״ͼ*/	
	private static final int CHARTTYPE_BAR_3D_H = 4;
	/*��ֱ������״ͼ*/
	private static final int CHARTTYPE_BAR_3D_V = 5;
	/*ƽ������ͼ*/
	private static final int CHARTTYPE_LINE_2D = 6;
	/*��������ͼ*/
	private static final int CHARTTYPE_LINE_3D = 7;
	
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
		System.out.println(qid+"===============================");
		return "success";
	}
	
	/**
	 * ���ͼ��
	 * @throws Exception 
	 */
	public JFreeChart getChart(){
		JFreeChart chart = null;
		Font font = new Font("����", 0, 20);
		QuestionStatisticsModel qsm = ss.statistics(Integer.parseInt(qid));
		DefaultPieDataset pieds = null;//��ͼ���ݼ�
		DefaultCategoryDataset cateds = null;//�������ݼ�
		
		//���첻ͬ�����ݼ�
		if(chartType<2){
			pieds = new DefaultPieDataset();
			for(OptionStatisticsModel om:qsm.getOsms()){
				pieds.setValue(om.getOptionLabel(), om.getCount());
			}
		}
		else{
			cateds = new DefaultCategoryDataset();
			for(OptionStatisticsModel osm :qsm.getOsms()){
				cateds.addValue(osm.getCount(), osm.getOptionLabel(), "");
			}
		}
		
		//�ж�Ҫ���ͼ��
		switch(chartType){
		case CHARTTYPE_PIE_2D:
			chart = ChartFactory.createPieChart(qsm.getQuestion().getTitle(), pieds, true, false, false);
		case CHARTTYPE_PIE_3D:
			chart = ChartFactory.createPieChart3D(qsm.getQuestion().getTitle(), pieds, true, true, true);
			//����ǰ��ɫ͸����
			chart.getPlot().setForegroundAlpha(0.6f);
			break;
		case CHARTTYPE_BAR_2D_H:
			chart = ChartFactory.createBarChart(qsm.getQuestion().getTitle(), "", "", cateds, PlotOrientation.HORIZONTAL, true, true, true);
			break;
		case CHARTTYPE_BAR_2D_V:
			chart = ChartFactory.createBarChart(qsm.getQuestion().getTitle(), "", "", cateds, PlotOrientation.VERTICAL, true, true, true);
		case CHARTTYPE_BAR_3D_H:
			chart = ChartFactory.createBarChart3D(qsm.getQuestion().getTitle(), "", "", cateds, PlotOrientation.HORIZONTAL, true, true, true);
		case CHARTTYPE_BAR_3D_V:
			chart = ChartFactory.createBarChart3D(qsm.getQuestion().getTitle(), "", "", cateds, PlotOrientation.VERTICAL, true, true, true);
			break;
		case CHARTTYPE_LINE_2D:
			chart = ChartFactory.createLineChart(qsm.getQuestion().getTitle(), "", "", cateds, PlotOrientation.VERTICAL, true, true, true);
			break;
		case CHARTTYPE_LINE_3D:
			chart = ChartFactory.createLineChart3D(qsm.getQuestion().getTitle(), "", "", cateds, PlotOrientation.HORIZONTAL, true, true, true);
			break;
		}
		//���ñ������ʾ������
		chart.getTitle().setFont(font);
		chart.getLegend().setItemFont(font);
		
		//���ñ�ͼ��Ч
		if(chart.getPlot() instanceof PiePlot){
			PiePlot piePlot = (PiePlot) chart.getPlot();
			piePlot.setLabelFont(font);
			piePlot.setExplodePercent(0, 0.1);
			piePlot.setStartAngle(-15);
			piePlot.setDirection(Rotation.CLOCKWISE);
			piePlot.setNoDataMessage("No Data to Display");
		}
		//���÷Ǳ�ͼ��Ч
		else{
			chart.getCategoryPlot().getRangeAxis().setLabelFont(font);
			chart.getCategoryPlot().getRangeAxis().setTickLabelFont(font);
			chart.getCategoryPlot().getDomainAxis().setLabelFont(font);
			chart.getCategoryPlot().getDomainAxis().setTickLabelFont(font);
		}
		
		return chart;
	}
}
