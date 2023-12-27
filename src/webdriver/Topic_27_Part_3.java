package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_27_Part_3 {
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
		// expliciWait = new WebDriverWait(driver, 15);
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	//@Test
	public void TC_01_Element_Found() {
		// Element có xuất hiện, không cần chờ hết timeout
		// Dù có set cả 2 loại wait thì không ảnh hưởng
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);// apply cho findElement
		expliciWait = new WebDriverWait(driver, 10);// apply cho các điều kiện của Element
		driver.get("https://www.facebook.com/");
		
		// Explicit
		System.out.println("Start explicit: " + getTimeStamp());
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		System.out.println("End explicit: " + getTimeStamp());
		// Implicit
		System.out.println("Start implicit: " + getTimeStamp());
		driver.findElement(By.id("email"));
		System.out.println("End explicit: " + getTimeStamp());
	}
	
	//@Test
	public void TC_02_Element_Not_Found() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.facebook.com/");
		
		// Implicit
		System.out.println("Start implicit: " + getTimeStamp());
		try {
			driver.findElement(By.id("selenium"));
		} catch (Exception e) {
			System.out.println("End explicit: " + getTimeStamp());
		}
		
	}

	@Test
	public void TC_03_Element_Not_Found_Implicit_Explicit() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		expliciWait = new WebDriverWait(driver, 10);
		driver.get("https://www.facebook.com/");
		
		// Implicit
		System.out.println("Start implicit: " + getTimeStamp());
		try {
			driver.findElement(By.id("selenium"));
		} catch (Exception e) {
			System.out.println("End explicit: " + getTimeStamp());
		}
		
		// Explicit
		System.out.println("Start explicit: " + getTimeStamp());
		try {
			expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("selenium")));
		} catch (Exception e) {
			System.out.println("End explicit: " + getTimeStamp());
		}
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public String getTimeStamp() {
		Date date = new Date();
		return date.toString();
	}

}
