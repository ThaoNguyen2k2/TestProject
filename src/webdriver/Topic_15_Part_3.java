package webdriver;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Part_3 {
	WebDriver driver;
	Alert alert;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random rand = new Random();
	Actions action;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDriver\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/chromedriver");
		}

		Map<String, Integer> prefs = new HashMap<String, Integer>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);
		//action = new Actions(driver);
		//expliciWait = new WebDriverWait(driver, 10);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// driver.get("");
	}

	//@Test
	public void TC_01_Fixed_In_Dom() {
		driver.get("https://ngoaingu24h.vn/");
		//sleepInSecond(10);
		
		By elementPopup = (By.xpath("//div[@id='modal-login-v1']//div[@class='modal-content']"));
		Assert.assertFalse(driver.findElement(elementPopup).isDisplayed());
		
		driver.findElement(By.xpath("//div[@id='button-login-dialog']//button[@class='login_ icon-before']")).click();
		Assert.assertTrue(driver.findElement(elementPopup).isDisplayed());
		
		driver.findElement(By.id("account-input")).sendKeys("9847435");
		driver.findElement(By.id("password-input" )).sendKeys("thaonguyen");
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//div[@id='modal-login-v1']//button[text()='Đăng nhập']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='modal-login-v1']//div[@class='row error-login-panel']")).getText(),"Tài khoản không tồn tại!");
	
	}
	

	@Test
	public void TC_02_Fixed_In_Dom() {
		driver.get("https://skills.kynaenglish.vn/");
		
		By loginPopup = By.cssSelector("div#k-popup-account-login");
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		
		//sleepInSecond(10);
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		
		driver.findElement(By.id("user-login")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("user-password")).sendKeys("123456");
		driver.findElement(By.id("btn-submit-login")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.id("password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
		
		driver.findElement(By.xpath("//button[@class='k-popup-account-close close']")).click();
		sleepInSecond(2);
		
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
	
	}

	//@Test
	public void TC_03_Fixed_Not_In_Dom() {
		driver.get("https://www.facebook.com/");
		
		By loginPopup = By.cssSelector("");
	}

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
