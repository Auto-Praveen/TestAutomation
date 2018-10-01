package com.test.util;

import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	public String file;
	public FileInputStream fls;
	public XSSFWorkbook wb;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;

	// how to get the exact row count, with only the rows which has data

	public ReadExcel(String file) throws Exception {

		this.file = file;
		fls = new FileInputStream(file);
		wb = new XSSFWorkbook(fls);
		wb.close();

	}

	public int getRowCount(String sheetName) throws Exception {

		/*int rowCount = wb.getSheet(sheetName).getLastRowNum()+1;
		System.out.println("Total rows in the sheet " + wb.getSheet(sheetName) + " is " + rowCount);
		return rowCount;*/
		
		int index = wb.getSheetIndex(sheetName);
		if(index==-1)
			return 0;
		else{
		sheet = wb.getSheetAt(index);
		int number=sheet.getLastRowNum()+1;
		return number;
		}
	}

	public int getColumnCount(String sheetName) throws Exception {

		XSSFRow row = wb.getSheet(sheetName).getRow(0);
		int columncount = row.getLastCellNum();
		System.out.println("Total number of columns are " + columncount);
		return columncount;
	}

	public String getCellData(String sheetName, String colName, int rowNum) {
		try {
			if (rowNum <= 0)
				return "";

			int index = wb.getSheetIndex(sheetName);
			int col_Num = -1;
			if (index == -1)
				return "";

			sheet = wb.getSheetAt(index);
			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {

				if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
					col_Num = i;
			}
			if (col_Num == -1)
				return "";

			sheet = wb.getSheetAt(index);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				return "";
			cell = row.getCell(col_Num);

			if (cell == null)
				return "";

			if (cell.getCellTypeEnum() == CellType.STRING)
				return cell.getStringCellValue();
			else if (cell.getCellTypeEnum() == CellType.NUMERIC || cell.getCellTypeEnum() == CellType.FORMULA) {
				String cellText = String.valueOf(cell.getNumericCellValue());
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// format in form of M/D/YY
					double d = cell.getNumericCellValue();


				}

				return cellText;
			} else if (cell.getCellTypeEnum() == CellType.BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());

		} catch (Exception e) {

			e.printStackTrace();
			return "row " + rowNum + " or column " + colName + " does not exist in xls";
		}
		
		
	}
	public static boolean verifyTestcaseMode(String tcName) {
		
		
		return false;		
	}
	
	public static void main(String[] args) throws Exception {
		ReadExcel re = new ReadExcel("E:\\MavenTestAutomation\\src\\main\\resources\\data.xlsx");
		System.out.println(re.getCellData("Sheet1", "TCID", 2));
		System.out.println(verifyTestcaseMode("tc_001"));
	}

}
