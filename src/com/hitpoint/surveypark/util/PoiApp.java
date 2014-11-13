package com.hitpoint.surveypark.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class PoiApp {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		//������
		HSSFWorkbook wb = new HSSFWorkbook();
		//������
		HSSFSheet sheet = wb.createSheet("first sheet");
		wb.createSheet("second sheet");
		//��
		HSSFRow row = sheet.createRow(0);
		//��Ԫ��
		HSSFCell cell = row.createCell(0);
		cell.setCellValue(false);
		row.createCell(1).setCellValue(Calendar.getInstance());
		row.createCell(2).setCellValue(new Date());
		row.createCell(3).setCellValue(123456789.98765);
		String str = "ddddddddddddddddddddddddddddddddd";
		row.createCell(4).setCellValue(new HSSFRichTextString(str));
		
		//�������ݸ�ʽ����
		HSSFDataFormat format = wb.createDataFormat();
		//��ʽ������
		HSSFCellStyle style = wb.createCellStyle();
		//������ʽ�����ݸ�ʽ
		style.setDataFormat(format.getFormat("yyyy-MM-dd hh:mm:ss"));
		//Ӧ����ʽ����Ԫ��
		row.getCell(1).setCellStyle(style);
		row.getCell(2).setCellStyle(style);
		//�����п�(��λ��1/20 ��)
		sheet.setColumnWidth(1, 3000);
		sheet.autoSizeColumn(2);
		sheet.setColumnWidth(4, 6000);
		
		//�Զ������ı�
		style = wb.createCellStyle();
		style.setWrapText(true);
		row.getCell(4).setCellStyle(style);
		
		HSSFRow row2 = sheet.createRow(1);
		row2.createCell(0).setCellValue("����");
		row2.createCell(1).setCellValue("����");
		row2.createCell(2).setCellValue("����");
		
		//�����и�
		row2.setHeightInPoints(50);
		sheet.setColumnWidth(0, 5000);
		sheet.setColumnWidth(1, 5000);
		sheet.setColumnWidth(2, 5000);
		
		//���ö��뷽ʽ
		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);//�����
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);//�϶���
		row2.getCell(0).setCellStyle(style);
		
		//���ö��뷽ʽ
		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//����
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//����
		row2.getCell(1).setCellStyle(style);

		//���ö��뷽ʽ
		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);//�Ҷ���
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_BOTTOM);//�¶���
		row2.getCell(2).setCellStyle(style);
		
		//����������ɫ�ʹ�С
		style = row2.getCell(1).getCellStyle();
		HSSFFont font = wb.createFont();
		font.setFontName("����Ҧ��");
		font.setFontHeightInPoints((short)30);		
		font.setItalic(true);
		font.setColor(HSSFColor.RED.index);
		style.setFont(font);
		
		//��doubleֵ���ø�ʽ
		style = wb.createCellStyle();
		style.setDataFormat(format.getFormat("#,###.000"));
		row.getCell(3).setCellStyle(style);
		wb.write(new FileOutputStream(new File("d:/poi.xls")));
		
	}
}
