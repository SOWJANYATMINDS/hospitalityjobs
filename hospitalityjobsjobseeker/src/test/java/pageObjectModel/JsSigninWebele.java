

	package pageObjectModel;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;

	import excelSetLibrary.ExcelDataConfig;
	import excelSetLibrary.ExcelDataConfig1;

	public class JsSigninWebele {

			static WebDriver driver;
			static ExcelDataConfig excel;	
			static ExcelDataConfig1 excel1;	
			static String projectPath;

		public void SetExcelBrowser() {
			excel = new ExcelDataConfig(".\\excel\\DataSheet.xlsx");
			excel1 = new ExcelDataConfig1(".\\excel\\numericdata.xlsx");
			System.setProperty("webdriver.gecko.driver",".\\lib\\geckodriver\\geckodriver.exe");
			projectPath = System.getProperty("user.dir");
			driver = new FirefoxDriver();
		}
		
		By jobscore = By.xpath("//div[@class='main-banner__head']//h1");
		By signinbutton = By.xpath("//a[@class='navbar__link navbar__login']");
		By username = By.name("username");
		By password = By.name("password");
		By login = By.xpath("//input[@value='Sign in']");
		
		
		
		
	public JsSigninWebele(WebDriver driver) {
			
		JsSigninWebele.driver = driver;
	}
		
	public void jobScoreele() {
		String text = driver.findElement(jobscore).getText();
		System.out.println("Jobs available : " + text);
	}

	//Signin elements
	public void signinele()
	{		
		driver.findElement(signinbutton).click();
	}
	public void userele(String uid)
	{
		driver.findElement(username).sendKeys(uid);
	}
	public void passele(String upwd)
	{
		driver.findElement(password).sendKeys(upwd);
	}
	public void loginele()
	{
		driver.findElement(login).click();
	}

	
	}
