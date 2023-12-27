package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_24_Part_3_02 {
	WebDriver driver;
	Alert alert;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random rand = new Random();
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDriver\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/chromedriver");
		}

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//Implicit wait set ở đâu thì nó sẽ apply từ đó trở xuống
		//Nếu bị gán lại -> thì sẽ dùng giá trị mới
		//Nếu ko set -> implicit wait mặc định = 0
	}
	
	

	@Test
	public void TC_01_Not_Enough_Time() {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		//Click start button
		driver.findElement(By.xpath("//button")).click();
		
		//Get text and verify
		Assert.assertEquals(driver.findElement(By.xpath("//h4")).getText(),"Hello World!");
		
	}
	
	@Test
	public void TC_02_Enough_Time() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		//Click start button
		driver.findElement(By.xpath("//button")).click();
		
		//Get text and verify
		Assert.assertEquals(driver.findElement(By.xpath("//h4")).getText(),"Hello World!");
		
	}
	
	@Test
	public void TC_03_More_Time() {
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		//Click start button
		driver.findElement(By.xpath("//button")).click();
		
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
