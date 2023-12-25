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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21_Part_3 {
	WebDriver driver;
	Alert alert;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random rand = new Random();
	JavascriptExecutor jsExecutor;

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
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		jsExecutor = (JavascriptExecutor) driver;
	}

	@Test
	public void TC_01_upload_One_File() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		// load file
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(beachFilePath);
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(computerFilePath);
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(mountainFilePath);

		// verifile file
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()= '" + beachFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()= '" + computerFileName + "']"))
				.isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()= '" + moutainFileName + "']")).isDisplayed());

		// Click upload
		List<WebElement> buttonUpload = driver
				.findElements(By.xpath("//table//button[@class='btn btn-primary start']"));

		for (WebElement btnUpload : buttonUpload) {
			btnUpload.click();
			sleepInSecond(2);
		}

		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + beachFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + computerFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + moutainFileName + "']")).isDisplayed());

		// Verify upload success
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + beachFileName + "')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + computerFileName + "')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + moutainFileName + "')]"));
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getElement(locator));
		return status;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
}
