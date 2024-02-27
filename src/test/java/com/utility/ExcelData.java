package com.utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData {
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static FileInputStream fi;
	public static void setCellData(String file,String xlsheet,int rownum,int colnum,String data) throws IOException{
//		System.out.println("line 20--"+file);
		fo=new FileOutputStream(file);
		wb=new XSSFWorkbook();
		ws=wb.createSheet(xlsheet);
		row=ws.createRow(rownum);
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		wb.write(fo);		
		wb.close();
		fo.close();
	}
    public static void setCellDataAgain(String file,String xlsheet,int rownum,int colnum,String data) throws IOException{
//    	System.out.println("line 32"+file);
    	fi=new FileInputStream(file);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fo=new FileOutputStream(file);
		wb.write(fo);		
		wb.close();
		fi.close();
		fo.close();
	}
    public static void setCellRowAgain(String file,String xlsheet,int rownum,int colnum,String data) throws IOException
	{
		fi=new FileInputStream(file);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.createRow(rownum);
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fo=new FileOutputStream(file);
		wb.write(fo);		
		wb.close();
		fi.close();
		fo.close();
	}
    public static void setNewSheet(String file,String xlsheet,int rownum,int colnum,String data) throws IOException
	{
		fi=new FileInputStream(file);
		wb=new XSSFWorkbook(fi);
		ws=wb.createSheet(xlsheet);
		row=ws.createRow(rownum);
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fo=new FileOutputStream(file);
		wb.write(fo);		
		wb.close();
		fi.close();
		fo.close();
	}
}
