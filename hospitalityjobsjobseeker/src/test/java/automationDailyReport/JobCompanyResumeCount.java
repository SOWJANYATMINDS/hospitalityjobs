package automationDailyReport;

import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;
import excelSetLibrary.ExcelDataConfig;
import excelSetLibrary.ExcelDataConfig1;
import utilities.AddTextToWorddoc;
import utilities.Screenshots;

public class JobCompanyResumeCount {
	WebDriver driver;
	ExcelDataConfig excel;	
	ExcelDataConfig1 excel1;	
	String Date, projectPath,addToWord,addToWord1,addToWord2,addToWord3,addToWord4,data;
	ATUTestRecorder recorder;
	
	@BeforeTest
	public void SetExcelBrowser() throws Exception 
	{
	recorder = new ATUTestRecorder("C:\\Users\\Ethreyas Admin\\Desktop\\Selenium generated videos","Daily report",false);
	recorder.start(); 
	excel = new ExcelDataConfig(".\\excel\\DataSheet.xlsx");
	excel1 = new ExcelDataConfig1(".\\excel\\numericdata.xlsx");
	System.setProperty("webdriver.gecko.driver",".\\lib\\geckodriver\\geckodriver.exe");
	projectPath = System.getProperty("user.dir");
	driver = new FirefoxDriver();

	 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	 //get current date time with Date()
	 Date date = new Date();
	 // Now format the date
	 String date1= dateFormat.format(date);
	 // Print the Date
	 Date= "Current Date: "+date1;
	 System.out.println(Date);
	}
	
	@Test(priority = 1,description="This test will print Number of Jobs, Companies, Resumes available now")
	public void details() throws Exception {
		driver.manage().timeouts().implicitlyWait(4,TimeUnit.SECONDS);
		driver.get("http:\\hospitalityjobs.com");
		driver.manage().window().maximize();
		//Livejobs count
		String text = driver.findElement(By.xpath("//div[@class='main-banner__head']//h1")).getText();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		//Taking live jobs count screenshot
		Screenshots.captureScreenshot(driver,"liveJobs");		
		//adding live jobs count to wordDoc
		addToWord = ("Job count : " + text);
		AddTextToWorddoc.textToWordDoc(addToWord, "C:\\Users\\Ethreyas Admin\\Desktop\\Success.docx");
		System.out.println(addToWord);
		//Companies count
		driver.findElement(By.xpath("//div[@class='visible-md visible-lg']//span[contains(text(),'Companies')]")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		String text1 = driver.findElement(By.xpath("//h1[@class='title__primary title__primary-small title__centered']")).getText();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		//Taking Companies count screenshot
		Screenshots.captureScreenshot(driver, "Companies count");
		//adding Companies count to WordDoc
		addToWord1 = ("Companies available :	" + text1);
		System.out.println(addToWord1);
		//resumes count
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@class='visible-md visible-lg']//span[contains(text(),'Resume Search')]")).click();
		String text2 = driver.findElement(By.xpath("//h1[@class='search-results__title col-sm-offset-3 col-xs-offset-0']")).getText();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		//Taking resumes count screenshot
		Screenshots.captureScreenshot(driver, "Resumes available");
		//adding resumes count to wordDoc
		addToWord2 = ("Resumes Count :	" + text2);
		System.out.println(addToWord2);
	
		
	}
	@AfterTest
	public void wordOutput() throws ATUTestRecorderException {
	
	  try {
			XWPFDocument document = new XWPFDocument();
			  XWPFParagraph paragraph = document.createParagraph();
			  XWPFRun run = paragraph.createRun();
			  run.setText(Date);
			  run.addBreak();
			  run.addBreak();
			  run.setText(addToWord);				  
			  run.addBreak();			
			  run.setFontSize(12);
			  FileOutputStream out = new FileOutputStream("C:\\Users\\Ethreyas Admin\\Desktop\\Success.docx");
			  document.write(out); 
			  out.close();
			  document.close();
		} catch (Exception e) {
			System.out.println(e);
			driver.close();

		}
	}
}

