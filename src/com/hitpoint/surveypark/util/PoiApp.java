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
		//工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		//工作表
		HSSFSheet sheet = wb.createSheet("first sheet");
		wb.createSheet("second sheet");
		//行
		HSSFRow row = sheet.createRow(0);
		//单元格
		HSSFCell cell = row.createCell(0);
		cell.setCellValue(false);
		row.createCell(1).setCellValue(Calendar.getInstance());
		row.createCell(2).setCellValue(new Date());
		row.createCell(3).setCellValue(123456789.98765);
		String str = "ddddddddddddddddddddddddddddddddd";
		row.createCell(4).setCellValue(new HSSFRichTextString(str));
		
		//创建数据格式对象
		HSSFDataFormat format = wb.createDataFormat();
		//格式化数据
		HSSFCellStyle style = wb.createCellStyle();
		//设置样式的数据格式
		style.setDataFormat(format.getFormat("yyyy-MM-dd hh:mm:ss"));
		//应用样式给单元格
		row.getCell(1).setCellStyle(style);
		row.getCell(2).setCellStyle(style);
		//设置列宽(单位：1/20 点)
		sheet.setColumnWidth(1, 3000);
		sheet.autoSizeColumn(2);
		sheet.setColumnWidth(4, 6000);
		
		//自动回绕文本
		style = wb.createCellStyle();
		style.setWrapText(true);
		row.getCell(4).setCellStyle(style);
		
		HSSFRow row2 = sheet.createRow(1);
		row2.createCell(0).setCellValue("左上");
		row2.createCell(1).setCellValue("中中");
		row2.createCell(2).setCellValue("右下");
		
		//设置行高
		row2.setHeightInPoints(50);
		sheet.setColumnWidth(0, 5000);
		sheet.setColumnWidth(1, 5000);
		sheet.setColumnWidth(2, 5000);
		
		//设置对齐方式
		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);//左对齐
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);//上对齐
		row2.getCell(0).setCellStyle(style);
		
		//设置对齐方式
		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//居中
		row2.getCell(1).setCellStyle(style);

		//设置对齐方式
		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);//右对齐
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_BOTTOM);//下对齐
		row2.getCell(2).setCellStyle(style);
		
		//设置字体颜色和大小
		style = row2.getCell(1).getCellStyle();
		HSSFFont font = wb.createFont();
		font.setFontName("方正姚体");
		font.setFontHeightInPoints((short)30);		
		font.setItalic(true);
		font.setColor(HSSFColor.RED.index);
		style.setFont(font);
		
		//给double值设置格式
		style = wb.createCellStyle();
		style.setDataFormat(format.getFormat("#,###.000"));
		row.getCell(3).setCellStyle(style);
		wb.write(new FileOutputStream(new File("d:/poi.xls")));
		
	}
}
