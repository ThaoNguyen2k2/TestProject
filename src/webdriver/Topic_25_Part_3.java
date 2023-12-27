package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_25_Part_3 {
	WebDriver driver;	
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait expliciWait;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDriver\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/chromedriver");
		}

		driver = new ChromeDriver();
		expliciWait = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	

	@Test
	public void TC_01_Not_Enough_Time() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		expliciWait = new WebDriverWait(driver, 3);
		//Click start button
		driver.findElement(By.xpath("//button")).click();
		
		//Thiếu thời gian để cho 1 element tiếp theo hoạt động được
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		
		//Get text and verify
		Assert.assertEquals(driver.findElement(By.xpath("//h4")).getText(),"Hello World!");
		
	}
	
	@Test
	public void TC_02_Enough_Time() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		expliciWait = new WebDriverWait(driver, 6);
		//Click start button
		driver.findElement(By.xpath("//button")).click();
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		
		//Get text and verify
		Assert.assertEquals(driver.findElement(By.xpath("//h4")).getText(),"Hello World!");
		
	}
	
	@Test
	public void TC_03_More_Time() {
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		expliciWait = new WebDriverWait(driver, 50);
		
		//Click start button
		driver.findElement(By.xpath("//button")).click();
		
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		//Get text and verify
		Assert.assertEquals(driver.findElement(By.xpath("//h4")).getText(),"Hello World!");
		
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
