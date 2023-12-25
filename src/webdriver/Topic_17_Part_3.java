package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Part_3 {
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

		driver = new ChromeDriver();
		action = new Actions(driver);	
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Fixed_Not_In_DOM() {
		driver.get("https://tiki.vn/");
		
		By loginPopup = By.cssSelector("div.ReactModal__Content");
		
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
		
		driver.findElement(By.xpath("//div[contains(@data-view-id,'header_account')]")).click();
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		
		driver.findElement(By.xpath("//input[@name='tel']")).sendKeys("092573289");
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//button[@class='btn-close']")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
	}
	
	//@Test
		public void TC_02_Popup_In_DOM() {
			driver.get("https://vnk.edu.vn/");
			sleepInSecond(20);
			
			By popup = By.xpath("//div[@id='tve-p-scroller']");
			
			if (driver.findElement(popup).isDisplayed()) {
				driver.findElement(By.xpath("div.tcb-icon-display")).click();
				sleepInSecond(3);
			}
			
			driver.findElement(By.xpath("//button[@class='btn btn-danger']")).click();
			sleepInSecond(5);
			
			Assert.assertEquals(driver.getTitle(), "Lịch khai giảng các khóa học tại VNK EDU | VNK EDU");
		}

		@Test
		public void TC_03_Popup_In_DOM() {
			driver.get("https://dehieu.vn/");
			sleepInSecond(10);
			
			By popup = By.cssSelector("div.popup-content");
			
			if (driver.findElements(popup).size() > 0 && (driver.findElements(popup).get(0).isDisplayed()))	{
				driver.findElement(By.id("popup-name")).sendKeys("Auto");
				driver.findElement(By.id("popup-email")).sendKeys("email");
				driver.findElement(By.id("popup-phone")).sendKeys("1697178");
				driver.findElement(By.xpath("//button[@class='close']")).click();
				sleepInSecond(3);
			}
			
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
