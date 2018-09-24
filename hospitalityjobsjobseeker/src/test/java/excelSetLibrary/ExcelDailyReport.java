package excelSetLibrary;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDailyReport {

	XSSFWorkbook wb;
	XSSFSheet sheet;
	
	public ExcelDailyReport(String excelPath) {
		
		try {
			File src = new File("C:\\Users\\Ethreyas Admin\\Desktop\\Daily Report.xlsx");
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheetAt(0);
		
		} catch (Exception e) {
		
			System.out.println(e.getMessage());
			}
	
		
	}
	
}

