
	package pageObjectModel;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.testng.annotations.BeforeTest;

	import excelSetLibrary.ExcelDataConfig;
	import excelSetLibrary.ExcelDataConfig1;

	public class JsSignupWebele {

		static WebDriver driver;
		static ExcelDataConfig excel;	
		static ExcelDataConfig1 excel1;	
		static String projectPath;


		
	@BeforeTest
	public static void SetExcelBrowser() throws Exception 
	{
		
		excel = new ExcelDataConfig(".\\excel\\DataSheet.xlsx");
		excel1 = new ExcelDataConfig1(".\\excel\\numericdata.xlsx");
		System.setProperty("webdriver.gecko.driver",".\\lib\\geckodriver\\geckodriver.exe");
		projectPath = System.getProperty("user.dir");
		driver = new FirefoxDriver();
	}
		By signupbutton= By.xpath("//a[contains(@class,'btn__blue')]");
		By jobseeker = By.xpath("//a[contains(text(),'Job Seeker')]");
		By fullname = By.id("FullName");
		By email = By.name("username");
		By password = By.name("password[original]");
		By confirmpassword = By.name("password[confirmed]");
		By referralcode = By.id("referral_code");
		By ecard = By.id("giftcard_enable");
		By register = By.xpath("//input[@value='Register']");
		public JsSignupWebele(WebDriver driver)//constructor
		{
		JsSignupWebele.driver= driver;
		}
		//jobs available
		
		
		//Signup elements
		public void signupButtonele() {
			driver.findElement(signupbutton).click();			
		}
		public void jobseekerLinkele() {
			driver.findElement(jobseeker).click();
		}
		public void fullnameele(String fln) {
			driver.findElement(fullname).sendKeys(fln);
		}
		public void emailele(String eml) {
			driver.findElement(email).sendKeys(eml);
		}
		public void passele(String psd) {
		driver.findElement(password).sendKeys(psd);
		}
		public void confirmpassele(String cpsd) {
		driver.findElement(confirmpassword).sendKeys(cpsd);
		}
		public void referralcodeele(String rfc) {
			driver.findElement(referralcode).sendKeys(rfc);
		}
		public void ecardele() {
			driver.findElement(ecard).click();
		}
		public void registerele() {
			driver.findElement(register).click();
		}

	}


