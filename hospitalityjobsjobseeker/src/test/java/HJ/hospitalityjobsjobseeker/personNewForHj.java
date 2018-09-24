package HJ.hospitalityjobsjobseeker;



import java.util.concurrent.TimeUnit;


	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.interactions.Actions;
	import org.testng.Assert;
	import org.testng.annotations.BeforeTest;
	import org.testng.annotations.Test;
	import excelSetLibrary.ExcelDataConfig;
	import excelSetLibrary.ExcelDataConfig1;
	import pageObjectModel.JsCreateResumeWebele;
	import pageObjectModel.JsSignupWebele;

	public class personNewForHj {
		static WebDriver driver;
		static ExcelDataConfig excel;	
		static ExcelDataConfig1 excel1;	
		static String projectPath;


		
	@BeforeTest
	public static void SetExcelBrowser() throws Exception 
	{
		//set Browser and Exceldata
		excel = new ExcelDataConfig(".\\excel\\DataSheet.xlsx");
		excel1 = new ExcelDataConfig1(".\\excel\\numericdata.xlsx");
		System.setProperty("webdriver.gecko.driver",".\\lib\\geckodriver\\geckodriver.exe");
		projectPath = System.getProperty("user.dir");
		driver = new FirefoxDriver();

	}

	@Test(priority = 1,description="This will Search for job with location")
	public void openAndJs() throws Exception {
		driver.get("http:\\hospitalityjobs.com");		
		driver.manage().timeouts().implicitlyWait(6,TimeUnit.SECONDS);
		// Search for job
		driver.findElement(By.id("keywords")).sendKeys("line cook"); 		
		driver.findElement(By.cssSelector("#GooglePlace")).sendKeys("Hyderabad, Telangana, India"); 
		driver.findElement(By.cssSelector(".quick-search__find")).click();  
		String title = driver.getTitle();
		Assert.assertTrue(title.contains("0 jobs found"));
		System.out.println("Found jobs related search");
		//Open job and click on apply now
		driver.findElement(By.xpath("//a[contains(text(),'Line cook')]")).click();  
		driver.findElement(By.xpath("//a[@class='btn details-footer__btn-apply btn__orange btn__bold']")).click(); 
		//driver.findElement(By.className("close")).click();  
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	//	driver.findElement(By.xpath("//a[contains(text(),'Job Seeker Registration")).click();
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.linkText("Job Seeker Registration"))).click().build().perform();
		
	}
	@Test(priority=2,description="This test will verify Signup function with valid details")
	public void signup() {
	//driver.findElement(By.xpath("//a[contains(@class,'navbar__link  btn__blue')]")).click();  
	//driver.findElement(By.xpath("//a[contains(text(),'Job Seeker')]")).click();  
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
	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	String source = driver.getPageSource();
	Assert.assertFalse(source.contains("Please enter"));
	System.out.println("Signed up successfully");
	}

	@Test(priority=3,description="This verifies Create resume function with valid details")
	public void createResume() {
		String data;
		JsCreateResumeWebele createResume = new JsCreateResumeWebele(driver);
		//createResume.myAccountele();
		//createResume.createResumeele();
		//createResume.postAResumeele();
		driver.manage().timeouts().implicitlyWait(6,TimeUnit.SECONDS);
		createResume.uploadPhotoele(projectPath+"\\images\\photo.jpg");
		data = excel.getData(4, 1, 0);
		createResume.jobTitleele(data);
		createResume.jobTypeele();
		createResume.categoryele();
		data = excel.getData(4, 1, 1);
		createResume.personalSummaryele(data);
		createResume.resumeUploadele(projectPath+"\\excel\\RESUME.docx");
		data = excel.getData(4, 1, 3);
		createResume.locationele(data);
		int data1 = excel1.getData1(0, 0, 0);
		createResume.phoneele(data1);
		data = excel.getData(4, 1, 5);
		createResume.positionele(data);
		data = excel.getData(4, 1, 6);
		createResume.companyele(data);
		createResume.fromele();
		createResume.toele();
		data = excel.getData(4, 1, 7);
		createResume.descriptionele(data);
		data = excel.getData(4, 1, 8);
		createResume.degreeele(data);
		data = excel.getData(4, 1, 9);
		createResume.universityele(data);
		createResume.fromInEduele();
		createResume.toInEduele();
		data = excel.getData(4, 1, 10);
		createResume.firstNameele(data);
		data = excel.getData(4, 1, 11);
		createResume.lastNameele(data);
		createResume.previewele();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		createResume.postele(); //This line makes resume active
		String source = driver.getPageSource();
		Assert.assertFalse(source.contains("Please enter"));
		System.out.println("Resume created successfully");

	}
	 @Test(priority=4,description="This test will apply for Job")
	public void applynow() throws Exception {
		 //click on jobs page and search for jobs
		driver.findElement(By.xpath("//div[@class='visible-md visible-lg']//span[contains(text(),'Jobs')]")).click();  
		driver.findElement(By.id("keywords")).sendKeys("line cook"); 
		driver.findElement(By.cssSelector("#GooglePlace")).sendKeys("Hyderabad, Telangana, India"); 
		driver.findElement(By.cssSelector(".quick-search__find")).click();  
		//Open job and click on apply now
		driver.findElement(By.xpath("//a[contains(text(),'Line cook')]")).click();  
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[@class='btn details-footer__btn-apply btn__orange btn__bold']")).click(); 
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//input[@class='btn__submit-modal btn btn__orange btn__bold']"))).click().build().perform();
		//driver.findElement(By.cssSelector("//a[@class='btn__submit-modal btn btn__orange btn__bold']")).click();
		String source = driver.getPageSource();
		Assert.assertFalse(source.contains("Thank you! Your application has been sent."));
		System.out.println("Applied to Job");
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		driver.findElement(By.className("close")).click();

	 }
		
	 @Test(priority=5,description= "This test will delete Jobseeker profile")
	 public void deleteProfile() throws Exception {
		 
		 driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		 Actions act = new Actions(driver);
		 act.moveToElement(driver.findElement(By.xpath("//a[@class='navbar__link btn__blue']"))).click().build().perform();
		 Thread.sleep(6000);
		 driver.findElement(By.xpath("//a[contains(text(),'Account Settings')]")).click();
		 driver.manage().timeouts().implicitlyWait(25,TimeUnit.SECONDS);
		 driver.findElement(By.xpath("//button[@type='button'][contains(text(),'Delete profile')]")).click();
		 driver.findElement(By.xpath("//button[@type='submit'][contains(text(),'Delete profile')]")).click();
		 String source = driver.getPageSource();
		 Assert.assertFalse(source.contains("You have successfully deleted your profile!"));
		 System.out.println("Successfully deleted your profile!");
		 driver.close();
		  }
	}

