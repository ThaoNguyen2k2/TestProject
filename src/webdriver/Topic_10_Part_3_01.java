package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Part_3_01 {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random rand = new Random();

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDriver\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/chromedriver");
		}

		driver = new ChromeDriver();
		expliciWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.get("");
	}

	//@Test
	public void TC_01() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");

		// CASE 1
		// Thẻ input bị che nên không tương tác được
		// Thẻ input lại dùng để verify được -> Vì hàm isSelected() chỉ work với thẻ
		// input

		// Thao tác chọn
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).click();
		sleepInSecond(3);

		// Verify chọn thành công
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).isSelected());

	}

	//@Test
	public void TC_02() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");

		// CASE 2
		// Thẻ khác với input để click (span/div/label...) -> đang hiển thị là được
		// Thẻ input lại không dùng để verify được

		// Thao tác chọn
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']//parent::label")).click();
		sleepInSecond(3);

		// Verify chọn thành công
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']//parent::label")).isSelected());
	}

	@Test
	public void TC_03() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");

		// CASE 2
		// Thẻ khác với input để click (span/div/label...) -> đang hiển thị là được
		// Thẻ input dùng để verify được

		// Thao tác chọn
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']//parent::label")).click();
		sleepInSecond(3);

		// Verify chọn thành công
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).isSelected());
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
