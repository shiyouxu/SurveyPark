package com.hitpoint.jfreechart;

import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 * 测试jfreechart
 */
public class PieChart {
	public static void main(String[] args) throws Exception {
		
		//创建饼图的数据集
		DefaultPieDataset ds = new DefaultPieDataset();
		ds.setValue("IBM", 4000);
		ds.setValue("Oracle", 6000);
		ds.setValue("JBOSS", 3300);
		ds.setValue("用友", 8000);
		//创建jfreechart对象(饼图)
		JFreeChart chart = ChartFactory.createPieChart("标题", ds, true, false, false);
		//设置标题字体
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));
		//设置提示条字体
		chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 15));
		//修改绘图区的字体
		PiePlot plot =  (PiePlot) chart.getPlot();
		plot.setLabelFont(new Font("宋体", Font.ITALIC, 15));
		//设置绘图区的背景图片
		plot.setBackgroundImage(ImageIO.read(new File("d:/Penguins.jpg")));
		//设置图表区的背景图片
		chart.setBackgroundImage(ImageIO.read(new File("d:/Hydrangeas.jpg")));
		//设置分离效果
		plot.setExplodePercent("IBM", 0.1f);
		
		//3D绘图中设置前景透明(0:完全透明；1:完全不透明)
		//plot.setForegroundAlpha(0.75f);
		
		//定制标签
		//默认是{0}：公司名；{1}：数量；{2}：百分比；{3}：总合
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}({3}/{1})--{2}"));
		
		ChartUtilities.saveChartAsJPEG(new File("d:/pie.jpg"), chart, 800, 600);

		
	}
}
