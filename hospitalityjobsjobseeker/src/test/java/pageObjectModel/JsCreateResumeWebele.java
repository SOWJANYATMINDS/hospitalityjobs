package pageObjectModel;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.support.ui.Select;

	import excelSetLibrary.ExcelDataConfig;
	import excelSetLibrary.ExcelDataConfig1;

	public class JsCreateResumeWebele {
		static WebDriver driver;
		static ExcelDataConfig excel;	
		static ExcelDataConfig1 excel1;	
		static String projectPath;

	public static void SetExcelBrowser() {
		excel = new ExcelDataConfig(".\\excel\\DataSheet.xlsx");
		excel1 = new ExcelDataConfig1(".\\excel\\numericdata.xlsx");
		System.setProperty("webdriver.gecko.driver",".\\lib\\geckodriver\\geckodriver.exe");
		projectPath = System.getProperty("user.dir");
		driver = new FirefoxDriver();
	}

	By jobScore = By.xpath("//div[@class='main-banner__head']//h1");
	By signin = By.xpath("//a[@class='navbar__link navbar__login']");
	By username = By.name("username");
	By password = By.name("password");
	By login = By.xpath("//input[@value='Sign in']");
	//Createresume function
	By myaccountbutton = By.xpath("//a[@class='navbar__link btn__blue']");
	By createresumebutton = By.xpath("//a[@class='btn btn__orange btn__bold']");
	By postaresume = By.xpath("//input[@name='proceed_to_posting']");
	By uploadphoto = By.cssSelector("#input_file_Photo");
	By jobtitle = By.xpath("//input[@id='Title']");
	By jobtype = By.xpath("//select[@name='EmploymentType']");
	By category = By.cssSelector(".ui-multiselect");
	By bookkeeperincat = By.xpath("//span[contains(text(),'Book Keeper and Accountants')]");
	By personalsummary = By.xpath("//iframe[@id='Skills_ifr']");
	By resumeupload = By.xpath("//input[@id='input_file_Resume']");
	By phone = By.xpath("//input[@id='Phone']");
	By position = By.xpath("//input[@id='WE_JobTitle']");
	By company = By.xpath("//input[@id='WE_Company']");
	By location = By.xpath("//input[@id='GooglePlace']");
	By from = By.xpath("//div[@id='complexFields_WorkExperience']//div[3]//input[1]");
	By month = By.cssSelector("span.month:nth-child(4)");
	By fromTo = By.xpath("//div[@id='complexFields_WorkExperience']//div[4]//input[1]");
	By monthTo = By.cssSelector("span.month:nth-child(7)");
	By description = By.xpath("//iframe[@id='WorkExperience[WE_Description][1]_ifr']");
	By degree = By.xpath("//input[@id='ED_DegreeSpecialty']");
	By university = By.xpath("//input[@id='ED_UniversityInstitution']");
	By fromInEdu = By.xpath("//div[@id='complexFields_Education']//div[3]//input[1]");
	By monthInEdu = By.cssSelector("span.month:nth-child(1)");
	By toFromInEdu =By.xpath("//div[@id='complexFields_Education']//div[4]//input[1]");
	By toMonthInEdu = By.cssSelector("span.month:nth-child(3)");
	By firstname = By.xpath("//input[@id='id_Resume_FirstName']");
	By lastname = By.xpath("//input[@id='id_Resume_LastName']");
	By preview = By.xpath("//input[@id='listingPreview']");
	By post = By.xpath("//input[@value='Post']");
	By logout = By.xpath("//a[contains(text(),'Logout')]");


	public JsCreateResumeWebele(WebDriver driver)//constructor
	{
		JsCreateResumeWebele.driver= driver;
	}
	//jobScore
	public void jobScoreele() {
		String text = driver.findElement(jobScore).getText();
		System.out.println("Jobs available : " + text);
	}

	//Signin elements
	public void signinele()
	{		
		driver.findElement(signin).click();
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
	//CreateJob elements
	public void myAccountele() 
	{
		driver.findElement(myaccountbutton).click();
	}
	public void createResumeele() 
	{
		driver.findElement(createresumebutton).click();
	}
	public void postAResumeele() 
	{
		driver.findElement(postaresume).click();//use from second resume post only
	}
	public void uploadPhotoele(String pto)
	{
		//pto = projectPath+"\\images\\photo.jpg";
		driver.findElement(uploadphoto).sendKeys(pto);
	}
	public void jobTitleele(String jte)
	{
		driver.findElement(jobtitle).sendKeys(jte);
	}
	public void jobTypeele()
	{
		WebElement Job_type=driver.findElement(jobtype);
		Select JobType = new Select(Job_type);
		JobType.selectByVisibleText("Full time");
	}
	public void categoryele()
	{
		driver.findElement(category).click();
		driver.findElement(bookkeeperincat).click();
	}
	public void personalSummaryele(String psy)
	{
		driver.findElement(personalsummary).sendKeys(psy);	
	}
	public void resumeUploadele(String rud)
	{
		driver.findElement(resumeupload).sendKeys(rud);	
	}
	public void locationele(String ltn)
	{
		driver.findElement(location).sendKeys(ltn);
	}	
	public void phoneele(int phn)
	{
		driver.findElement(phone).sendKeys(String.valueOf(phn));	
	}
	public void positionele(String psn)
	{
		driver.findElement(position).sendKeys(psn);	
	}
	public void companyele(String cpy)
	{
		driver.findElement(company).sendKeys(cpy);	
	}
	public void fromele()
	{
		driver.findElement(from).click();
		driver.findElement(month).click();
	}
	public void toele()
	{
		driver.findElement(fromTo).click();
		driver.findElement(monthTo).click();
	}
	public void descriptionele(String dsn) 
	{
		driver.findElement(description).sendKeys(dsn);
	}
	public void degreeele(String dge)
	{
		driver.findElement(degree).sendKeys(dge);
	}
	public void universityele(String uvy)
	{
		driver.findElement(university).sendKeys(uvy);
	}
	public void fromInEduele()
	{
		driver.findElement(fromInEdu).click();
		driver.findElement(monthInEdu).click();
	}
	public void toInEduele()
	{
		driver.findElement(toFromInEdu).click();
		driver.findElement(toMonthInEdu).click();
	}

	public void firstNameele(String fne)
	{
		driver.findElement(firstname).sendKeys(fne);
	}
	public void lastNameele(String lne)
	{
		driver.findElement(lastname).sendKeys(lne);
	}
	public void previewele()
	{
		driver.findElement(preview).click();
	}
	public void postele()
	{
		driver.findElement(post).click();	
	}

	}

