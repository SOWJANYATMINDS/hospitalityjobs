package automationDailyReport;

import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import atu.testrecorder.exceptions.ATUTestRecorderException;
import excelSetLibrary.ExcelDataConfig;
import excelSetLibrary.ExcelDataConfig1;
import pageObjectModel.JsSignupWebele;
import utilities.Screenshots;

public class SignupAndDeleteProfile {
	WebDriver driver;
	ExcelDataConfig excel;	
	ExcelDataConfig1 excel1;	
	String projectPath,addToWord4;
	
	@Test(priority=2,description="This test will Sign in with valid details")
	public void signin() throws Exception 
	{
	
		excel = new ExcelDataConfig(".\\excel\\DataSheet.xlsx");
		excel1 = new ExcelDataConfig1(".\\excel\\numericdata.xlsx");
		System.setProperty("webdriver.gecko.driver",".\\lib\\geckodriver\\geckodriver.exe");
		projectPath = System.getProperty("user.dir");
		driver = new FirefoxDriver();
		driver.get("https:/hospitalityjobs.com");
		
		driver.findElement(By.xpath("//a[contains(@class,'navbar__link  btn__blue')]")).click();  
		driver.findElement(By.xpath("//a[contains(text(),'Job Seeker')]")).click();  
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		JsSignupWebele signup = new JsSignupWebele(driver);
		String data = excel.getData(6,1,0);
		signup.fullnameele(data);
		data = excel.getData(6,1,1);
		signup.emailele(data);
		data = excel.getData(6,1,2);
		signup.passele(data);
		driver.manage().timeouts().implicitlyWait(4,TimeUnit.SECONDS);
		data = excel.getData(6,1,3);
		signup.confirmpassele(data);
		data = excel.getData(6,1,4); 
		signup.referralcodeele(data);
		signup.ecardele();
		signup.registerele();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		Screenshots.captureScreenshot(driver, "Jobseeker signup");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		String source = driver.getPageSource();
		Assert.assertFalse(source.contains("Please enter"));
		//Adding result to word
		addToWord4 = ("Successfully signed up as Jobseeker");
		System.out.println(addToWord4);
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		//action class is used to click button/links in modal windows
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//a[@class='navbar__link btn__blue']"))).click().build().perform();
		Thread.sleep(6000);
		driver.findElement(By.xpath("//a[contains(text(),'Account Settings')]")).click();
		driver.manage().timeouts().implicitlyWait(25,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[@type='button'][contains(text(),'Delete profile')]")).click();
		driver.findElement(By.xpath("//button[@type='submit'][contains(text(),'Delete profile')]")).click();		
		
	}
	@AfterTest
	public void wordOutput() throws ATUTestRecorderException {
	
	  try {
			XWPFDocument document = new XWPFDocument();
			  XWPFParagraph paragraph = document.createParagraph();
			  XWPFRun run = paragraph.createRun();
			  
			  run.setText(addToWord4);
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
		
	}
}



