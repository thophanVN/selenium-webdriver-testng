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
import org.openqa.selenium.support.Color;


public class Topic_04_RegisterAtMailChimp {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeMethod
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",projectPath+"/browserDrivers/chromedriver");
		driver = new ChromeDriver();
		driver.get("https://login.mailchimp.com/signup/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	@Test
	public void TC01_RegisterAtMailChimp() {
		WebElement email = driver.findElement(By.cssSelector("#email"));
		WebElement userName = driver.findElement(By.xpath("//input[@name='username']"));
		WebElement password = driver.findElement(By.cssSelector("#new_password"));
		
		//password hint
		WebElement passHintOneNumber = driver.findElement(By.xpath("//li[text()='One number']"));
		
		//input email,username
		email.sendKeys("thphan05@gmail.com");
		userName.clear();
		userName.sendKeys("thphan06");
		
		//input password
		password.sendKeys("1");
		
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		//Verify password hint one number is displayed and disabled.
		Assert.assertEquals(passHintOneNumber.isDisplayed(), true );
		Assert.assertEquals(Color.fromString(passHintOneNumber.getCssValue("color")).asHex(), "#bdbbb9");
		System.out.println(Color.fromString(passHintOneNumber.getCssValue("color")).asHex());
		Assert.assertEquals(passHintOneNumber.getAttribute("class").contains("completed"), true);
		System.out.println(passHintOneNumber.getAttribute("class").contains("completed"));
	}
	
	@AfterMethod
	public void afterClass() {
		driver.quit();
	}
}