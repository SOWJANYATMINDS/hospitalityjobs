package HJ.hospitalityjobsjobseeker;

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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;
import excelSetLibrary.ExcelDataConfig;
import excelSetLibrary.ExcelDataConfig1;
import pageObjectModel.JsSigninWebele;
import pageObjectModel.JsSignupWebele;
import utilities.AddTextToWorddoc;
import utilities.Screenshots;

public class DailyReportDetails {
	
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
//	AddTextToWorddoc wordDoc = new AddTextToWorddoc ();

	/*try {
		PrintStream myconsole = new PrintStream(new File("C:\\Users\\Ethreyas Admin\\Desktop\\Daily report.txt"));
		System.setOut(myconsole);
		
		} catch(Exception e) {
		
		}*/
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

	@Test(priority=2,description="This test will Sign in with valid details")
	public void signin() throws Exception 
	{
	
		JsSigninWebele signin = new JsSigninWebele(driver);
  		//driver.get("http:\\hospitalityjobs.com");
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
	

	@Test(priority=3,description="This test will verify Signup function with valid details")
	public void signup() throws Exception {
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
	
	String searchResult1;
	@Test(priority=4,dataProvider= "data provider")	
	public void multipleJobSearch(String location) throws Exception {
//		WebDriverWait wait = new WebDriverWait(driver,60);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".visible-md > ul:nth-child(1) > li:nth-child(1) > a:nth-child(1) > span:nth-child(1)")));
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
		
//		driver.findElement(By.id("GooglePlace")).clear();
//		driver.findElement(By.id("GooglePlace")).sendKeys("Sarasota, FL, USA");
//		WebDriverWait wait1 = new WebDriverWait(driver,60);
//		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#ajax-refine-search > div > div.current-search > div")));
//		driver.findElement(By.xpath("//div[@class='quick-search__inner-pages hidden-xs-480']//button[@type='submit'][contains(text(),'Find Jobs')]")).click();
//		driver.manage().timeouts().implicitlyWait(28,TimeUnit.SECONDS);
//		String text1 = driver.findElement(By.xpath("//h1[@class='search-results__title col-sm-offset-3 col-xs-offset-0']")).getText();
//		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
//		//Screenshots.captureScreenshot(driver, "Sarasota, FL, USA");
//		System.out.println(text1+" in Sarasota, FL, USA");
//		
//		driver.findElement(By.id("GooglePlace")).clear();
//		driver.findElement(By.id("GooglePlace")).sendKeys("Twinsburg");
//		driver.findElement(By.xpath("//div[@class='quick-search__inner-pages hidden-xs-480']//button[@type='submit'][contains(text(),'Find Jobs')]")).click();
//		Thread.sleep(5000);
//		driver.manage().timeouts().implicitlyWait(28,TimeUnit.SECONDS);
//		String text2 = driver.findElement(By.xpath("//h1[@class='search-results__title col-sm-offset-3 col-xs-offset-0']")).getText();
//		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
//		//Screenshots.captureScreenshot(driver, "Twinsburg");
//		System.out.println(text2+" in  Twinsburg");		
//		driver.findElement(By.id("GooglePlace")).clear();
//		driver.findElement(By.id("GooglePlace")).sendKeys("Stafford County, VA, USA");
//		driver.findElement(By.xpath("//div[@class='quick-search__inner-pages hidden-xs-480']//button[@type='submit'][contains(text(),'Find Jobs')]")).click();
//		Thread.sleep(5000);
//		driver.manage().timeouts().implicitlyWait(28,TimeUnit.SECONDS);
//		String text3 = driver.findElement(By.xpath("//h1[@class='search-results__title col-sm-offset-3 col-xs-offset-0']")).getText();
//		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
//		//Screenshots.captureScreenshot(driver, "Stafford County, VA, USA");
//		System.out.println(text3+" in Stafford County, VA, USA");
//	
//		driver.findElement(By.id("GooglePlace")).clear();
//		driver.findElement(By.id("GooglePlace")).sendKeys("USA");
//		driver.findElement(By.xpath("//div[@class='quick-search__inner-pages hidden-xs-480']//button[@type='submit'][contains(text(),'Find Jobs')]")).click();
//		Thread.sleep(2000);
//		driver.manage().timeouts().implicitlyWait(28,TimeUnit.SECONDS);
//		String text4 = driver.findElement(By.xpath("//h1[@class='search-results__title col-sm-offset-3 col-xs-offset-0']")).getText();
//		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
//		//Screenshots.captureScreenshot(driver, "USA");
//		System.out.println(text4+" in USA");
		
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
				  run.setText(Date);
				  run.addBreak();
				  run.addBreak();
				  run.setText(addToWord);				  
				  run.addBreak();
				  run.setText(addToWord1);
				  run.addBreak();
				  run.setText(addToWord2);
				  run.addBreak();
				  run.setText(addToWord3);
				  run.addBreak();
				  run.setText(addToWord4);
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
		
		
