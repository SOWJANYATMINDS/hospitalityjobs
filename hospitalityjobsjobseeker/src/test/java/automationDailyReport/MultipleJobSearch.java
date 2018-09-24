package automationDailyReport;

import java.io.FileOutputStream;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;
import excelSetLibrary.ExcelDataConfig;
import excelSetLibrary.ExcelDataConfig1;

public class MultipleJobSearch {
	
	WebDriver driver;
	ExcelDataConfig excel;	
	ExcelDataConfig1 excel1;	
	String projectPath,searchResult1;
	ATUTestRecorder recorder;
	
	
	
	@Test(priority=4,dataProvider= "data provider")	
	public void multipleJobSearch(String location) throws Exception {
//		WebDriverWait wait = new WebDriverWait(driver,60);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".visible-md > ul:nth-child(1) > li:nth-child(1) > a:nth-child(1) > span:nth-child(1)")));
		excel = new ExcelDataConfig(".\\excel\\DataSheet.xlsx");
		excel1 = new ExcelDataConfig1(".\\excel\\numericdata.xlsx");
		System.setProperty("webdriver.gecko.driver",".\\lib\\geckodriver\\geckodriver.exe");
		projectPath = System.getProperty("user.dir");
		driver = new FirefoxDriver();	
		driver.get("http:\\hospitalityjobs.com");
	
		driver.findElement(By.xpath("//div[@class='visible-md visible-lg']//span[contains(text(),'Jobs')]")).click();
		driver.findElement(By.id("GooglePlace")).clear();
		driver.findElement(By.id("GooglePlace")).sendKeys(location);
		driver.findElement(By.xpath("//div[@class='quick-search__inner-pages hidden-xs-480']//button[@type='submit'][contains(text(),'Find Jobs')]")).click();
		WebDriverWait waits = new WebDriverWait(driver,60);
		waits.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#ajax-refine-search > div > div.current-search > div")));
		String text = driver.findElement(By.xpath("//h1[@class='search-results__title col-sm-offset-3 col-xs-offset-0']")).getText();
		//Screenshots.captureScreenshot(driver, "Mason City, IA 50401, USA");
		//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		searchResult1 = (text+" in "+ location);
		System.out.println(searchResult1);
		
	}
		
		@DataProvider(name = "data provider")
		public Object[] MultipleJobData(){
			ExcelDataConfig config = new ExcelDataConfig(".\\excel\\daily report sheet.xlsx");
			int rows = config.getRowCount(0);
			Object[] data = new Object[rows];
			
			for (int i=0;i<rows;i++)
			{
				data[i] = config.getData(0, i, 0);

			}
			
			return data;
			}
		@AfterTest
		public void wordOutput() throws ATUTestRecorderException {
		
		  try {
				XWPFDocument document = new XWPFDocument();
				  XWPFParagraph paragraph = document.createParagraph();
				  XWPFRun run = paragraph.createRun();
				  run.addBreak();
				  run.setText(searchResult1);
				  run.addBreak();
				  run.setFontSize(12);
				  FileOutputStream out = new FileOutputStream("C:\\Users\\Ethreyas Admin\\Desktop\\Success.docx");
				  document.write(out); 
				  out.close();
				  document.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		driver.close();
		recorder.stop();

	}


}
