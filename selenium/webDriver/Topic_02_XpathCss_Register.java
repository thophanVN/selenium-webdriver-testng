package webDriver;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;

public class Topic_02_XpathCss_Register {
	// Khai báo biến cho web driver
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeMethod
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");

		// khởi tạo browser
		driver = new ChromeDriver();

		// set thời gian chờ để tìm elements
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.MICROSECONDS);

		// mở trang alada
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		// maximize the browser
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01_RegisterWithEmptyData() {
		// click on Register button
		driver.findElement(By.xpath("//form[@name='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

		// Verify error message at "Ho va Ten" field
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtFirstname-error']")).getText(),"Vui lòng nhập họ tên");

		// Verify error message at "Dia chi email" field
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(),"Vui lòng nhập email");

		// Verify error message at "Nhap lai email" field
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(),"Vui lòng nhập lại địa chỉ email");

		// Verify error message at "Mat Khau" field
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(),"Vui lòng nhập mật khẩu");

		// Verify error message at "Nhap lai mat khau" field
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(),"Vui lòng nhập lại mật khẩu");

		// Verify error message at "Dien thoai" field
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(),"Vui lòng nhập số điện thoại.");
	
	}
	
	@Test
	public void TC_02_Locator_RegisterWithInvalidMail() {
	
		// Input invalid data for email and re-email field. Then, click on Register button
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("123@456@789");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("123@456@789");
		driver.findElement(By.xpath("//form[@name='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		// Verify error message
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(),"Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(),"Email nhập lại không đúng"); //Bug system.
	}
	
	@Test
	public void TC_03_Locator_RegisterWithIncorrectConfirmEmail() {
		//refresh page
		driver.navigate().refresh();
		// Input data for email and re-email field. Then, click on Register button
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("johnwick@gmail.net");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("johnwick@gmail.com");
		driver.findElement(By.xpath("//form[@name='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		// Verify error message 
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(),"Email nhập lại không đúng");
	}
	
	@Test
	public void TC_04_Locator_RegisterPwdWithCharLessThan6Chars() {
		//refresh page
		driver.navigate().refresh();
		// Input data for email and re-email field. Then, click on Register button
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("John Wick");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("johnwick@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("johnwick@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0987654321");
		driver.findElement(By.xpath("//form[@name='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		//Verify message error in both password and re-password
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
	}
	
	@Test
	public void TC_05_Locator_RegisterIncorrectConfirmPassword() {
		// Input data for email and re-email field. Then, click on Register button
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("John Wick");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("johnwick@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("johnwick@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123789");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0987654321");
		driver.findElement(By.xpath("//form[@name='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		//Verify message error in both password and re-password
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Mật khẩu bạn nhập không khớp");
	}
	
	@Test
	public void TC_06_Locator_RegisterIncorrectPhoneNumber() {
		//refresh page
		driver.navigate().refresh();
		// Input data for email and re-email field. Then, click on Register button
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("John Wick");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("johnwick@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("johnwick@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("09876543");
		driver.findElement(By.xpath("//form[@name='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		//Verify message error in both password and re-password
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Số điện thoại phải từ 10-11 số.");
		
	}
	
	public void sleepInSec(long sec) {
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void afterClass() {
		driver.quit();
	}
}