package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDriver\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/chromedriver");
		}

		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		expliciWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.get("");
	}

	// @Test
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
				driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input"))
						.isSelected());

	}

	// @Test
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

	//@Test
	public void TC_03() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");

		// CASE 3
		// Thẻ khác với input để click (span/div/label...) -> đang hiển thị là được
		// Thẻ input dùng để verify được

		// Thao tác chọn
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']//parent::label")).click();
		sleepInSecond(3);

		// Verify chọn thành công
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input"))
						.isSelected());
	}

	@Test
	public void TC_04() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");

		// CASE 4
		// Thẻ input bị ẩn nhưng vẫn dùng để click
		// Hàm click() và WebElement nó sẽ không thao tác vào element bị ẩn được
		// Dùng hàm click() của Javascrip để click(click vào element bị ẩn được)
		// Dùng JavascripExecutor của Selenium
		// Thẻ input dùng để verify được

		// Selenium cung cấp 1 thư viện có thể nhúng các đoạn code JS vào kịch bản test
		// được -> JavaScripExecutor
		// Thao tác chọn
		By radioButton = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input");
		jsExecutor.executeScript("arguments[0].click();",
				driver.findElement(radioButton));
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(radioButton).isSelected());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	// đây là 1 phần khác
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
