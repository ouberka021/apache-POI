package com.cydeo.tests;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelRead {


    @Test
    public void readExcel_file() throws IOException {
       String path = "SampleData.xlsx";
       // to read Excel file you need to load it to FileInputStream
            FileInputStream fileInputStream = new FileInputStream(path);
        // workbook>sheet>row>cell
        // 1 - create a workbook
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        // 2 - we need to get specific sheet from currently opened workbook
        //XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFSheet sheet = workbook.getSheet("Employees");
        // 3- select row and cell
        //TODO: Print out Mary's cell
       // sheet.getRow(1);
        // print ouberka
        XSSFCell cell = sheet.getRow(3).getCell(1);
        System.out.println("Row number 3 and cell number 1 : "+cell);
        // print Mary
        XSSFCell cell2 = sheet.getRow(1).getCell(0);
        System.out.println("Row number 1 and cell number 0 : "+cell2);

        // 3 - iterate over rows and columns
        for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
            XSSFRow row = sheet.getRow(rowNum);
            for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
                XSSFCell cell3 = row.getCell(cellNum);
                if (cell!=null) {
                    System.out.print(cell3.getStringCellValue() + "\t");
                }
            }
            System.out.println();
        }



        //Return the count of used cells only
        // start counting from 1
        int usedRows = sheet.getPhysicalNumberOfRows();
        System.out.println("usedRows= "+usedRows);
        /*
        Returns the number from top to cell to bottom cell and it
        doesn't care if the cell is empty or not starts counting from 0
        */
        int LastUsedRow = sheet.getLastRowNum();
        System.out.println("LastUsedRow ="+LastUsedRow);
// TODO : Create logic to print Vinods name dynamically

for(int rowNum = 0; rowNum < LastUsedRow; rowNum++){
    if(sheet.getRow(rowNum).getCell(0).toString().equals("Hassan")){
        System.out.println("sheet.getRow(rowNum).getCell(0) : "+sheet.getRow(rowNum).getCell(0));
        break;
    }
}


        // 4 - close the workbook
       workbook.close();

    }


}
