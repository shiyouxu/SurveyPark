package com.hitpoint.jfreechart;

import java.awt.Font;
import java.io.File;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChart {

	public static void main(String[] args) throws Exception {
		//创建类别数据集
		DefaultCategoryDataset ds = new DefaultCategoryDataset();
		ds.addValue(3400, "IBM", "一季度");
		ds.addValue(3100, "Oracle", "一季度");
		ds.addValue(2200, "用友", "一季度");
		
		ds.addValue(3500, "IBM", "二季度");
		ds.addValue(3200, "Oracle", "二季度");
		ds.addValue(3000, "用友", "二季度");
		
		ds.addValue(3800, "IBM", "三季度");
		ds.addValue(2100, "Oracle", "三季度");
		ds.addValue(3300, "用友", "三季度");
		
		//创建JfreeChart对象
		String title1 = "前三季度各大公司JEE AS销量统计";
		String title2 = "季度";
		String title3 = "销量(单位：万台)";
		JFreeChart chart = ChartFactory.createBarChart3D(title1, title2, title3, ds, PlotOrientation.VERTICAL, true, false, false);
		//中文问题
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 25));
		//提示条字体
		chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 15));
		//得到绘图区
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		//Domain轴标签字体(域轴即是横轴)
		plot.getDomainAxis().setLabelFont(new Font("宋体", Font.PLAIN, 15));
		//Domain轴小标签字体
		plot.getDomainAxis().setTickLabelFont(new Font("宋体", Font.PLAIN, 15));
		//range轴的标签字体(范围轴即是纵轴)
		plot.getRangeAxis().setLabelFont(new Font("宋体", Font.PLAIN, 15));
		
		
		
		ChartUtilities.saveChartAsJPEG(new File("d:/bar.jpg"), chart, 800, 600);
	}

}
