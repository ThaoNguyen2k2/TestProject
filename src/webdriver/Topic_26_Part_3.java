package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_26_Part_3 {
	WebDriver driver;	
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait expliciWait;
	
	String beachFileName = "Beach.jpg";
	String computerFileName = "computer.jpg";
	String moutainFileName = "Mountain.jpg";

	String beachFilePath = projectPath + "\\uploadFile\\" + beachFileName;
	String computerFilePath = projectPath + "\\uploadFile\\" + computerFileName;
	String mountainFilePath = projectPath + "\\uploadFile\\" + moutainFileName;


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


	//@Test
	public void TC_01() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		expliciWait = new WebDriverWait(driver, 15);
		
		//Wait Datime Picker hiển thị
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.calendarContainer>div")));
		
		//Verify cho Selected Dates là không ngày nào được chọn
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='datesContainer']//span")).getText(), "No Selected Dates to display.");
		
		//Wait ngày 19 được phép Click
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='27']")));
		
		//Click ngày 19
		driver.findElement(By.xpath("//a[text()='27']")).click();
		
		//Wait cho Ajax Icon loading biến mất (invisible)
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']/parent::div[contains(@id,'RadCalendar')]")));
		
		//Wait cho ngày được click 
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='rcSelected']//a")));
		
		assertEquals(driver.findElement(By.xpath("//div[@class='datesContainer']//span")).getText(), "Wednesday, December 27, 2023");
	}
	
	@Test
	public void TC_02() {
		driver.get("https://gofile.io/uploadFiles");
		expliciWait = new WebDriverWait(driver, 45);
		
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='filesUpload']//button")));
		
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(beachFilePath + "\n" + computerFilePath + "\n" + mountainFilePath);
		
		//Wait cho các loading icon của từng file biến mất
		expliciWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.progress-bar")));
		
		//Wait cho Upload message thành công được visible
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='mainUpload']//div[text()='Your files have been successfully uploaded']")));
		
		//Verify message
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='mainUpload']//div[text()='Your files have been successfully uploaded']")).isDisplayed());
	
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
