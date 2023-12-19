package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Part_3 {
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
		//expliciWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.get("");
	}

	//@Test
	public void TC_01() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		List <WebElement> listNumber = driver.findElements(By.xpath("//ol[@id='selectable']/li"));//contain all number
		
		//Click source > hold mouse > taget number and drop
		action.clickAndHold(listNumber.get(0)).moveToElement(listNumber.get(7)).release().perform();
		sleepInSecond(5);
		
		List <WebElement> selectedNumber = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(selectedNumber.size(), 8);
		//Assert.a
	}

	@Test
	public void TC_02_ClickAndHoldRandom() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		List <WebElement> listNumber = driver.findElements(By.xpath("//ol[@id='selectable']/li"));//contain all number
		
		//Press Ctrl
		action.keyDown(Keys.CONTROL).perform();
		
		//Click radom
		action.click(listNumber.get(0))
		.click(listNumber.get(3))
		.click(listNumber.get(5))
		.click(listNumber.get(10)).perform();
		//Drop 
		
		List <WebElement> selectedNumber = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(selectedNumber.size(), 4);
	}

	//@Test
	public void TC_03() {

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
