package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Part_3 {
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDriver\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/chromedriver");
		}

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.navigate().to("https://www.w3schools.com/");
		System.out.println(driver.getTitle());
		//Thread.sleep(2000);
	}

	@Test
	public void TC_01_() {
		WebDriver driver = new ChromeDriver();

		WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='email']"));
		// emailTextbox.clear();
		// emailTextbox.sendKeys("");

		// Find 1 element
		driver.findElement(By.xpath("//button[@id='login']")).click();

		// Find many element
		List<WebElement> checkboxes = driver.findElements(By.xpath(""));

		// Open URL
		driver.get("https://www.w3schools.com/");

		// Return current URL
		driver.getCurrentUrl();
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.w3schools.com/");

		// Return Current Page Title
		Assert.assertEquals(driver.getTitle(), "W3Schools Online Web Tutorials");

		// Get All of ID Window/ Tab of current driver (active driver)
		Set<String> allIDs = driver.getWindowHandles();

		// List: duplicate
		// Set: distinct
		Options opt = driver.manage();
		opt.getCookies();

		// Element Waiting time
		Timeouts time = opt.timeouts();
		time.implicitlyWait(5, TimeUnit.SECONDS);

		// Page Load Time
		time.pageLoadTimeout(5, TimeUnit.SECONDS);

		Window win = opt.window();
		win.fullscreen();
		win.maximize();

		// Test GUI
		win.getPosition();
		win.getSize();

		Navigation nav = driver.navigate();
		nav.back();
		nav.refresh();
		nav.forward();

		nav.to("https://www.w3schools.com/");

		TargetLocator tar = driver.switchTo();
		tar.alert();
		tar.frame("");
		tar.window("");
	}
}
