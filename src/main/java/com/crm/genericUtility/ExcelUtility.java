package com.crm.genericUtility;

import com.crm.IConstant;
import org.apache.poi.ss.usermodel.*;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtility {

        @Test
        public static Object[][] readDataFromExcel(String sheetName) throws IOException {
            FileInputStream fis = new FileInputStream(IConstant.excelPath);
            Workbook wb = WorkbookFactory.create(fis);
            Sheet sheet = wb.getSheet(sheetName);
            int lastRow = sheet.getLastRowNum();
            int lastCol = sheet.getRow(0).getLastCellNum();
            Object[][] data = new Object[lastRow][lastCol];
            DataFormatter value=new DataFormatter();
            for(int i=0;i<lastRow;i++)
            {
                for(int j=0;j<lastCol;j++)
                {
                    Cell cell = sheet.getRow(i + 1).getCell(j);
                    data[i][j]= value.formatCellValue(cell);
                   //data[i][j] = sheet.getRow(i+1).getCell(j).getStringCellValue();
                }

            }
            System.out.println("Data is: "+data);
            return data;
        }

        public static String toReadDataFromExcel(String sheetName, int rowNum, int cellNum) throws Throwable {
            FileInputStream fis = new FileInputStream(IConstant.excelPath);
            Workbook wb = WorkbookFactory.create(fis);
            //String data = wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
            DataFormatter dm = new DataFormatter();
            Cell cell = wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum);
            String value = dm.formatCellValue(cell);
            //String data = wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).toString();
            wb.close();
            return value;
        }
    public static void toWriteDataToExcel(String sheetName, String value,int rowNum, int cellNum) throws Throwable {
        FileInputStream fis = new FileInputStream("./src\\main\\resources\\NinzaTestData.xlsx");
        Workbook wb = WorkbookFactory.create(fis);
        wb.getSheet(sheetName).createRow(rowNum).createCell(cellNum).setCellValue(value);
        wb.close();
    }

        public int togetRowCount(String sheetName) throws Throwable {
            FileInputStream fis = new
                    FileInputStream("./src\\main\\resources\\NinzaTestData.xlsx");
            Workbook wb = WorkbookFactory.create(fis);
            int rowCount = wb.getSheet(sheetName).getLastRowNum();
            wb.close();
            return rowCount;
        }

//    public static void main(String[] args) throws Throwable {
//        String teamSize  = ExcelUtility.toReadDataFromExcel("Campaigns",1,1);
//        System.out.println(teamSize);
//        ExcelUtility.toWriteDataToExcel("Campaigns","ABC",1,0);
//    }
    }
