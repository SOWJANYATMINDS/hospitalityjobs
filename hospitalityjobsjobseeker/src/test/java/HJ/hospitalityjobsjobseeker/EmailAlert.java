package HJ.hospitalityjobsjobseeker;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

@Test
public class EmailAlert {
	public void readAlert() {
	System.setProperty("webdriver.gecko.driver",".\\lib\\geckodriver\\geckodriver.exe");
	WebDriver driver = new FirefoxDriver();
	//Zoho mail login
	driver.get("https:/mail.zoho.com");
	driver.findElement(By.className("signin")).click();
	driver.findElement(By.cssSelector("#lid")).sendKeys("automation@ethreyas.com");
	driver.findElement(By.cssSelector("#pwd")).sendKeys("Ethreyas@123");
	driver.findElement(By.id("signin_submit")).click();	
	driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
//	driver.findElement(By.cssSelector(".zmSearchTB")).sendKeys("Daily report Summary");
//	driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
//	driver.findElement(By.cssSelector(".zmSearchTB")).sendKeys(Keys.ENTER);
//	driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	
	String content =driver.findElement(By.xpath("")).getText();
	System.out.println(content);

	if(content.contains("PM")||content.contains("AM"))
	{
	System.out.println("email alert received successfully");
	}
	 
	
	//driver.findElement(By.xpath("//div[@id='1537515867662110003']//span[contains(text(),'Daily Report Summary')]")).click();
	//div[@class='SCmc SC_sw']//div[@id='1537515867662110003']//div[@class='zmLDate'][contains(text(),'1:14 PM')]
	
	
	
	
	
	
	
	
	
	
	
//	driver.manage().deleteAllCookies();	
//	driver.get("https:/accounts.google.com/signin");
//	driver.findElement(By.cssSelector("#identifierId")).sendKeys("employermail1234");
//	driver.findElement(By.xpath("//div[@id='identifierNext']//content[@class='CwaK9']")).click();
//	driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Ethreyas@123");
//	driver.findElement(By.xpath("//div[@id='identifierNext']//content[@class='CwaK9']")).click();
	}
	
}
