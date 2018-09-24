package key_Features;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import excelSetLibrary.ExcelDataConfig;

public class JobSearch {
	//static String projectPath;
	
	@Test(dataProvider = "Search data" )
	public static void jobSearch(String keyword, String location) {

		System.setProperty("webdriver.gecko.driver",".\\lib\\geckodriver\\geckodriver.exe");
		//projectPath = System.getProperty("user.dir");
		WebDriver driver = new FirefoxDriver();
		driver.get("http:\\hospitalityjobs.com");		
		driver.manage().timeouts().implicitlyWait(6,TimeUnit.SECONDS);
		// Search for job
		driver.findElement(By.id("keywords")).sendKeys(keyword); 		
		driver.findElement(By.cssSelector("#GooglePlace")).sendKeys(location); 
		driver.findElement(By.cssSelector(".quick-search__find")).click();		
		String count = driver.findElement(By.xpath("//h1[@class='search-results__title col-sm-offset-3 col-xs-offset-0']")).getText();
		Assert.assertFalse(count.equals("0 jobs found"),"For search using "+keyword+" "+location+" 0 jobs found");
		System.out.println("For search using "+keyword+" "+location+" "+count);
		driver.manage().timeouts().implicitlyWait(6,TimeUnit.SECONDS);
		driver.quit();
		
	}
	
	@DataProvider(name = "Search data")
	public Object[][] passData(){
		ExcelDataConfig config = new ExcelDataConfig(".\\excel\\DataSheet.xlsx");
		int rows = config.getRowCount(0);
		Object[][] data = new Object[rows][2];
		
		for (int i=0;i<rows;i++)
		{
			data[i][0] = config.getData(7, i, 0);
			data[i][1] = config.getData(7, i, 1);
			
		}
		
		return data;
		}
	
	
	
	 
	
}
