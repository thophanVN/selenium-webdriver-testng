package webDriver;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;

public class Topic_05_OpenHRM {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String username,pwd;
	
	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver",projectPath+"/browserDrivers/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	@Test
	public void TC_01_CreateNewEmployee() {
		username = "Admin";
		pwd = "admin123";
		// Login Page title
		driver.get("https://opensource-demo.orangehrmlive.com/");
		
		//Input user name, pwd to login and click on to login button
		driver.findElement(By.cssSelector("#divUsername #txtUsername")).sendKeys(username);
		driver.findElement(By.cssSelector("#divUsername #txtUsername")).sendKeys(pwd);
		driver.findElement(By.cssSelector("#divLoginButton #btnLogin")).click();
		
		//Verify go to dashboard page successfully
		Assert.assertEquals(driver.getCurrentUrl().contains("dashboard"), true);
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
	
	public void sleepInSec(Long sec) {
		try {
			Thread.sleep(sec*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}