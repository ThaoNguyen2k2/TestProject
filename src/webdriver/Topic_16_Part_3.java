package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Part_3 {
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

	//@Test
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
	
	@Test
	public void TC_02_DoubleClick() {
		driver.get("https://www.facebook.com/");
		
		By newAccountPopup = By.xpath("//div[text()='Sign Up']/parent::div/parent::div");
		
		Assert.assertEquals(driver.findElements(newAccountPopup).size(), 0);
		
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElements(newAccountPopup).size(), 1);
		
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Automation");
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("FC");
		driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys("084578635");
		driver.findElement(By.xpath("//input[@name='reg_passwd__']")).sendKeys("567891011");
		new Select(driver.findElement(By.id("day"))).selectByVisibleText("18");
		new Select(driver.findElement(By.id("month"))).selectByVisibleText("Aug");
		new Select(driver.findElement(By.id("year"))).selectByValue("1999");
		driver.findElement(By.xpath("//label[text()='Female']/following-sibling::input")).click();
		
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElements(newAccountPopup).size(), 0);
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
