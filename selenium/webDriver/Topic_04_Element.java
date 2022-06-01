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


public class Topic_04_Element {
	WebDriver driver;
	WebElement element;
	String projectPath = System.getProperty("user.dir");

	@BeforeMethod
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",projectPath+"/browserDrivers/chromedriver");
		driver = new ChromeDriver();
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void TC01_VerifyElementIsDisplayed() {
		//Verify email is displayed.
		Assert.assertEquals(true, driver.findElement(By.cssSelector("input[name='user_email']")).isDisplayed());	
		
		//Verify Age under 18 selection is displayed.
		Assert.assertEquals(true, driver.findElement(By.xpath("//label[text()='Under 18']")).isDisplayed());	
		
		//Verify Education is displayed.
		Assert.assertEquals(true, driver.findElement(By.cssSelector("textarea[name='user_edu']")).isDisplayed());	
		
		//Verify user5 is not displayed.
		Assert.assertEquals(false, driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed());
		driver.findElement(By.xpath("//h5[contains(text(),\"User5\")]/parent::div//preceding-sibling::img")).click();	
	}
	
	@Test
	public void TC02_VerifyElementIsEnabled() {
		//Verify email, Age under18,education is enabled for inputting.
		Assert.assertEquals(true, driver.findElement(By.cssSelector("input[name='user_email']")).isEnabled());	
		Assert.assertEquals(true, driver.findElement(By.cssSelector("input[id='under_18']")).isEnabled());	
		Assert.assertEquals(true, driver.findElement(By.cssSelector("textarea[id='edu']")).isEnabled());
		Assert.assertEquals(true, driver.findElement(By.cssSelector("select[id='job1']")).isEnabled());
		Assert.assertEquals(true, driver.findElement(By.cssSelector("select[id='job2']")).isEnabled());
		Assert.assertEquals(true, driver.findElement(By.cssSelector("input[name='user_interest']")).isEnabled());
		Assert.assertEquals(true, driver.findElement(By.cssSelector("input[id='slider-1']")).isEnabled());
	}
	
	@AfterMethod
	public void afterClass() {
		driver.quit();
		
	}
}