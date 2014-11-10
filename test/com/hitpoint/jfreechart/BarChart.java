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
		//����������ݼ�
		DefaultCategoryDataset ds = new DefaultCategoryDataset();
		ds.addValue(3400, "IBM", "һ����");
		ds.addValue(3100, "Oracle", "һ����");
		ds.addValue(2200, "����", "һ����");
		
		ds.addValue(3500, "IBM", "������");
		ds.addValue(3200, "Oracle", "������");
		ds.addValue(3000, "����", "������");
		
		ds.addValue(3800, "IBM", "������");
		ds.addValue(2100, "Oracle", "������");
		ds.addValue(3300, "����", "������");
		
		//����JfreeChart����
		String title1 = "ǰ�����ȸ���˾JEE AS����ͳ��";
		String title2 = "����";
		String title3 = "����(��λ����̨)";
		JFreeChart chart = ChartFactory.createBarChart3D(title1, title2, title3, ds, PlotOrientation.VERTICAL, true, false, false);
		//��������
		chart.getTitle().setFont(new Font("����", Font.BOLD, 25));
		//��ʾ������
		chart.getLegend().setItemFont(new Font("����", Font.PLAIN, 15));
		//�õ���ͼ��
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		//Domain���ǩ����(���ἴ�Ǻ���)
		plot.getDomainAxis().setLabelFont(new Font("����", Font.PLAIN, 15));
		//Domain��С��ǩ����
		plot.getDomainAxis().setTickLabelFont(new Font("����", Font.PLAIN, 15));
		//range��ı�ǩ����(��Χ�ἴ������)
		plot.getRangeAxis().setLabelFont(new Font("����", Font.PLAIN, 15));
		
		
		
		ChartUtilities.saveChartAsJPEG(new File("d:/bar.jpg"), chart, 800, 600);
	}

}
