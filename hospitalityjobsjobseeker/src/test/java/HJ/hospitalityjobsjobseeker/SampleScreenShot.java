package HJ.hospitalityjobsjobseeker;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class SampleScreenShot {
	
	@Test
	public void takeScreenShot() throws Exception {
	System.setProperty("webdriver.gecko.driver",".\\lib\\geckodriver\\geckodriver.exe");
	WebDriver driver = new FirefoxDriver();
	driver.get("http:\\hospitalityjobs.com");		
	TakesScreenshot ts= (TakesScreenshot)driver;
	File source= ts.getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(source, new File("./screenshots/some.png"));
	System.out.println("Taken Screenshot");
	
	
	
	
	}

}
