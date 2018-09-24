package automationDailyReport;

import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import atu.testrecorder.exceptions.ATUTestRecorderException;
import excelSetLibrary.ExcelDataConfig;
import excelSetLibrary.ExcelDataConfig1;
import pageObjectModel.JsSigninWebele;
import utilities.Screenshots;

public class SigninAndLogout {
	WebDriver driver;
	ExcelDataConfig excel;	
	ExcelDataConfig1 excel1;	
	String projectPath,addToWord3;

	@Test(priority=2,description="This test will Sign in with valid details")
	public void signin() throws Exception 
	{
		excel = new ExcelDataConfig(".\\excel\\DataSheet.xlsx");
		excel1 = new ExcelDataConfig1(".\\excel\\numericdata.xlsx");
		System.setProperty("webdriver.gecko.driver",".\\lib\\geckodriver\\geckodriver.exe");
		projectPath = System.getProperty("user.dir");
		driver = new FirefoxDriver();

		JsSigninWebele signin = new JsSigninWebele(driver);
  		driver.get("http:\\hospitalityjobs.com");
   		//signin.jobScoreele();
		signin.signinele();
		String data = excel.getData(0,2,0);
		signin.userele(data);
		data = excel.getData(0,2,1);
		signin.passele(data);
		signin.loginele();	
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		Screenshots.captureScreenshot(driver, "Jobseeker Signin");
		String source = driver.getPageSource();
		Assert.assertTrue(source.contains("My Account"));
		//Adding result to word
		addToWord3 = ("Successfully signed in as Jobseeker");
		System.out.println(addToWord3);
		driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	
		
	}
	
	@AfterTest
	public void wordOutput() throws ATUTestRecorderException {
	
	  try {
			XWPFDocument document = new XWPFDocument();
			  XWPFParagraph paragraph = document.createParagraph();
			  XWPFRun run = paragraph.createRun();
			  run.addBreak();
			  run.setText(addToWord3);
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
