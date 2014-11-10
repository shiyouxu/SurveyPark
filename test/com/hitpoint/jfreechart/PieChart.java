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
 * ����jfreechart
 */
public class PieChart {
	public static void main(String[] args) throws Exception {
		
		//������ͼ�����ݼ�
		DefaultPieDataset ds = new DefaultPieDataset();
		ds.setValue("IBM", 4000);
		ds.setValue("Oracle", 6000);
		ds.setValue("JBOSS", 3300);
		ds.setValue("����", 8000);
		//����jfreechart����(��ͼ)
		JFreeChart chart = ChartFactory.createPieChart("����", ds, true, false, false);
		//���ñ�������
		chart.getTitle().setFont(new Font("����", Font.BOLD, 20));
		//������ʾ������
		chart.getLegend().setItemFont(new Font("����", Font.PLAIN, 15));
		//�޸Ļ�ͼ��������
		PiePlot plot =  (PiePlot) chart.getPlot();
		plot.setLabelFont(new Font("����", Font.ITALIC, 15));
		//���û�ͼ���ı���ͼƬ
		plot.setBackgroundImage(ImageIO.read(new File("d:/Penguins.jpg")));
		//����ͼ�����ı���ͼƬ
		chart.setBackgroundImage(ImageIO.read(new File("d:/Hydrangeas.jpg")));
		//���÷���Ч��
		plot.setExplodePercent("IBM", 0.1f);
		
		//3D��ͼ������ǰ��͸��(0:��ȫ͸����1:��ȫ��͸��)
		//plot.setForegroundAlpha(0.75f);
		
		//���Ʊ�ǩ
		//Ĭ����{0}����˾����{1}��������{2}���ٷֱȣ�{3}���ܺ�
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}({3}/{1})--{2}"));
		
		ChartUtilities.saveChartAsJPEG(new File("d:/pie.jpg"), chart, 800, 600);

		
	}
}
