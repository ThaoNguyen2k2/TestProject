package webdriver;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Part_3 {
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
	public void TC_01() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Lấy ID của tab hiện tại
		String basicFormWindowID = driver.getWindowHandle();
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(3);

		switchToWindowByID(basicFormWindowID);

		driver.findElement(By.xpath("//textarea[@title='Tìm kiếm']")).sendKeys("Automation");
		sleepInSecond(3);

		// Case 1: Chỉ có duy nhất 2 window hoặc 2 tab
		// Case 2: Nhiều hơn 2 window hoặc 2 tab

		Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");
		String ggWindowID = driver.getWindowHandle();
		
		switchToWindowByID(ggWindowID);
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
	}

	//@Test
	public void TC_02_Title() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Lấy ID của tab hiện tại
		String basicFormWindowID = driver.getWindowHandle();
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(3);

		switchToWindowByTitle("Googe");
		
		driver.findElement(By.xpath("//textarea[@title='Tìm kiếm']")).sendKeys("Automation");
		sleepInSecond(3);

		// Case 1: Chỉ có duy nhất 2 window hoặc 2 tab
		// Case 2: Nhiều hơn 2 window hoặc 2 tab

		Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");
		String ggWindowID = driver.getWindowHandle();
		
		switchToWindowByTitle("Selenium WebDriver");
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
	}
	
	@Test
	public void TC_03() {
		driver.get("http://live.techpanda.org/index.php/mobile.html");
		
		driver.findElement(By.xpath("//a[@title='Sony Xperia']/parent::h2/parent::div//a[text()='Add to Compare']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='messages']//span")).getText(),"The product Sony Xperia has been added to comparison list.");

		driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/parent::div//a[text()='Add to Compare']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='messages']//span")).getText(),"The product Samsung Galaxy has been added to comparison list.");
	
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
	
		//String currentWindow = driver.getWindowHandle();
		switchToWindowByTitle("Products Comparison List - Magento Commerce");
		
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Compare Products")).isDisplayed());
	}
	
	
	//Dùng cho 2 Window/tab
	public void switchToWindowByID(String otherID) {
		// Lấy hết các ID ra
		Set<String> allWindowID = driver.getWindowHandles();
		// Sau đó dùng vòng lặp duyệt qua và kiểm tra
		// Nếu như ID nào mà khác ID của Parent thì Switch
		for (String id : allWindowID) {
			if (!id.equals(otherID)) {
				driver.switchTo().window(id);
			}
		}
		sleepInSecond(3);
	}
	
	//Dùng cho >2 Window/tab
	public void switchToWindowByTitle(String pageTitle) {
		// Lấy hết các ID ra
		Set<String> allWindowID = driver.getWindowHandles();
		// Sau đó dùng vòng lặp duyệt qua và kiểm tra
		for (String id : allWindowID) {
			driver.switchTo().window(id);
			//Lấy Title
			String actualPageTitle = driver.getTitle();
			
			if (actualPageTitle.equals(pageTitle)) {
				break;
			}
		}
		sleepInSecond(3);
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
