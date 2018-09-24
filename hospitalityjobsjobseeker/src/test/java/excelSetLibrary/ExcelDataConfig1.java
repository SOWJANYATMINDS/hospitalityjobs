package excelSetLibrary;


	import java.io.File;
	import java.io.FileInputStream;

	import org.apache.poi.xssf.usermodel.XSSFSheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;

	public class ExcelDataConfig1 {
		XSSFWorkbook wb;
		XSSFSheet sheet1;
		
		public ExcelDataConfig1(String excelPath){
			
			try {
				File src = new File(".\\excel\\numericdata.xlsx");
				FileInputStream fis = new FileInputStream(src);
				wb = new XSSFWorkbook(fis);
				
			
			} catch (Exception e) {
			
				System.out.println(e.getMessage());
				}
		
			
		}
			
		public int getData1(int sheetnumber,int row,int column) 
		{
			sheet1 = wb.getSheetAt(sheetnumber);
			int data1 = (int) sheet1.getRow(row).getCell(column).getNumericCellValue();
			return data1;
		}

	}


