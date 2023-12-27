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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_23_Part_3 {
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
		driver.manage().window().maximize();
		expliciWait = new WebDriverWait(driver, 10);
	}

	// @Test
	public void TC_01_Visible_Display_Visibility() {
		driver.get("https://www.facebook.com/");

		// 1. Có trên UI
		// 2. Có trong HTML

		// Wait cho email textbox hiển thị
		// Wait cho email textbox hiển thị trong vòng 10s
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		driver.findElement(By.id("email")).sendKeys("automationtest@gmail.com");

	}

	//@Test
	public void TC_02_InVisible_UnDisplay_Visibility_I() {
		driver.get("https://www.facebook.com/");

		// 1. không có trên UI
		// 2. Có trong HTML

		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		driver.findElement(By.id("email")).sendKeys("automationtest@gmail.com");

		// Wait cho Re-enter email textbox không hiển thị trong vòng 10s
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
	}

	// @Test
	public void TC_02_InVisible_UnDisplay_Visibility_II() {
		driver.get("https://www.facebook.com/");

		// 1. không có trên UI (bắt buộc)
		// 2. Có trong HTML

		// Wait cho Re-enter email textbox không hiển thị trong vòng 10s
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
	}

	// @Test
	public void TC_03_Present() {
		driver.get("https://www.facebook.com/");

		// 1. Có trên UI
		// 2. Có trong cây HTML (bắt buộc)

		// Wait cho email address textbox presence trong vòng 10s
		expliciWait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
	}

	//@Test
	public void TC_03_Present_II() {
		driver.get("https://www.facebook.com/");

		// 1. Không có trên UI
		// 2. Có trong cây HTML (bắt buộc)

		// Wait cho email address textbox presence trong vòng 10s
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		expliciWait.until(ExpectedConditions.presenceOfElementLocated(By.name("reg_email_confirmation__")));
	}

	@Test
	public void TC_04_Staleness() {
		driver.get("https://www.facebook.com/");

		// 1. không có trên UI (bắt buộc)
		// 2. Không có trong HTML

		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		
		// Phase 1: Element có trong HTML
		expliciWait.until(ExpectedConditions.presenceOfElementLocated(By.name("reg_email_confirmation__")));
		WebElement reEnterEmail = driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']"));
		
		//Thao tác với element khác làm cho element re-enter email không còn trong DOM nữa
		
		//Close popup
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div//preceding-sibling::img")).click();
		
		//Wait Re-enter Email textbox không còn trong DOM trong vòng 10s
		expliciWait.until(ExpectedConditions.stalenessOf(reEnterEmail));
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
