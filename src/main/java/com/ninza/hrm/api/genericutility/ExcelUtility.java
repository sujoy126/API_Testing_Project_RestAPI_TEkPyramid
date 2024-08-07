package com.ninza.hrm.api.genericutility;

import java.io.FileInputStream;

import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * 
 * @author Sujoy_mondal
 *
 */
public class ExcelUtility {
	/**
	 * 
	 * @param sheetName
	 * @param rowNum
	 * @param cellNum
	 * @return
	 * @throws Throwable
	 */
	public String getDataFromExcel(String sheetName, int rowNum, int cellNum) throws Throwable {
		FileInputStream fis = new FileInputStream("./testData/testScriptData.xlsx");
		Workbook book = WorkbookFactory.create(fis);

		DataFormatter df = new DataFormatter();
		String data = df.formatCellValue(book.getSheet(sheetName).getRow(rowNum).getCell(cellNum));
		book.close();
		return data;
	}

	public int geRowCount(String SheetName) throws Throwable {
		FileInputStream fis = new FileInputStream("./testData/testScriptData.xlsx");
		Workbook book = WorkbookFactory.create(fis);
		int rowCount = book.getSheet(SheetName).getLastRowNum();
		book.close();
		return rowCount;
	}

	public void setDataIntoExcel(String sheetName, int rowNum, int cellNum) throws Throwable {
		FileInputStream fis = new FileInputStream("./testData/testScriptData.xlsx");
		Workbook book = WorkbookFactory.create(fis);

		DataFormatter df = new DataFormatter();
		df.formatCellValue(book.getSheet(sheetName).createRow(rowNum).createCell(cellNum));

		FileOutputStream fos = new FileOutputStream("./testData/testScriptData.xlsx");
		book.write(fos);
		book.close();

	}

//	public void getMultiDataFromExcel(String sheet, int firstRowNum, int lastRownum, int firstCellNum, int lastCellNum) throws Throwable {
//		
//		FileInputStream fis = new FileInputStream("./testData/testScriptData.xlsx");
//		Workbook book = WorkbookFactory.create(fis);
//		Sheet sh = book.getSheet(sheet);
//		
//		DataFormatter df = new DataFormatter();
//		for(int i=firstRowNum; i<=sh.getLastRowNum(); i++) {
//			
//			df.formatCellValue(sh.getRow(i).getCell(lastCellNum));
//		}
//		
//	}
}
