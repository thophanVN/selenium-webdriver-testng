package webDriver;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Topic_04_Browser {
	WebDriver driver;
	WebElement element;
	String projectPath = System.getProperty("user.dir");

	@BeforeMethod
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",projectPath+"/browserDrivers/chromedriver");
		driver = new ChromeDriver();
		driver.get("http://live.techpanda.org/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void TC01_VerifyUrl() {
		//click on My Account
		driver.findElement(By.xpath("//*[@class='footer-container']//*[@title='My Account']")).click();
		
		//Verify url after clicking on My Account
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		
		//click on Create an Account
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		
		//Verify register page
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
	}
	
	
	@Test
	public void TC02_getTitle() {
		//click on My Account
		driver.findElement(By.xpath("//*[@class='footer-container']//*[@title='My Account']")).click();
				
		//Verify title after clicking on My Account
		String title = driver.getTitle();
		Assert.assertEquals(title, "Customer Login");
		
		//click on Create an Account
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
				
		//Verify title
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}
	
	@Test
	public void TC03_navigation() {
		//click on My Account and Create an Account on next page
		driver.findElement(By.xpath("//*[@class='footer-container']//*[@title='My Account']")).click();
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		
		//verify URL
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
		
		//back to login page
		driver.navigate().back();
		
		//Verify url of login page
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		
		//Foward to next page(register page) and verify Title
		driver.navigate().forward();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}
	
	
	@Test
	//verify source code page contains Create an Account
	public void TC04_VerifySourceCodePage() {
		//click on My Account
		driver.findElement(By.xpath("//*[@class='footer-container']//*[@title='My Account']")).click();
		
		//Verify source code page contains Login or create an Account
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		
		//click on Create an Account
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		
		//Verify source code page contains: Create an Account
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
	}
	
	@AfterMethod
	public void afterClass() {
		driver.quit();
		
	}
}