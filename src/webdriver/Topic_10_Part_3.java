package webdriver;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Part_3 {
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

	// @Test
	public void TC_01_JQuerry() {
		driver.get("https://www.fahasa.com/customer/account/create");

		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

		// Verify button login
		By loginButton = By.cssSelector("button.fhs-btn-login");
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());

		String LoginButtonBackground = driver.findElement(loginButton).getCssValue("backgroung-image");
		System.out.println(LoginButtonBackground);

		driver.findElement(By.cssSelector("input#login_username")).sendKeys("0268971688");
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
		LoginButtonBackground = driver.findElement(loginButton).getCssValue("background-color");// get color
		Color loginButtonBackColor = Color.fromString(LoginButtonBackground);// convert to Color
		Assert.assertEquals(loginButtonBackColor.asHex().toUpperCase(), "#C92127");// change to Hex
		System.out.println(LoginButtonBackground);
	}

	@Test
	public void TC_02_Checkbox_Radio_Single() {

		driver.get("https://automationfc.github.io/multiple-fields/");

		// Click checkbox
		driver.findElement(By.xpath("//label[contains(text(), 'Diabetes')]/preceding-sibling::input")).click();

		// click radio button
		driver.findElement(By.xpath("//label[contains(text(), \"I don't have a diet plan\")]/preceding-sibling::input"))
				.click();

		// Verify
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(), 'Diabetes')]/preceding-sibling::input"))
				.isSelected());
		Assert.assertTrue(driver
				.findElement(
						By.xpath("//label[contains(text(), \"I don't have a diet plan\")]/preceding-sibling::input"))
				.isSelected());

		// Checkbox bỏ chọn được
		driver.findElement(By.xpath("//label[contains(text(), 'Diabetes')]/preceding-sibling::input")).click();
		Assert.assertFalse(driver
				.findElement(By.xpath("//label[contains(text(), 'Diabetes')]/preceding-sibling::input")).isSelected());

		// Radio không thể bỏ chọn được
		driver.findElement(By.xpath("//label[contains(text(), \"I don't have a diet plan\")]/preceding-sibling::input"))
				.click();
		Assert.assertTrue(driver
				.findElement(
						By.xpath("//label[contains(text(), \"I don't have a diet plan\")]/preceding-sibling::input"))
				.isSelected());
	}

	//@Test
	public void TC_03_Checkbox_Multiple() {

		driver.get("https://automationfc.github.io/multiple-fields/");

		List<WebElement> allCheckbox = driver.findElements(By.cssSelector("input.form-checkbox"));

		// Chọn checkbox có giá trị cho sẵn
		for (WebElement checkbox : allCheckbox) {
			if (checkbox.getAttribute("value").equals("Diabetes"))
				checkbox.click();
			// sleepInSecond(1);
		}

		for (WebElement checkbox : allCheckbox) {
			Assert.assertTrue(checkbox.isSelected());
			// sleepInSecond(1);
		}
	}

	@Test
	public void TC_04_DefaultCheckbox() {

		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		
		if (!driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']//preceding-sibling::input")).isSelected())
		{
			driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']//preceding-sibling::input")).click();
		}
		sleepInSecond(1);

		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']//preceding-sibling::input")).isSelected());
		
		if (driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']//preceding-sibling::input")).isSelected())
		{
			driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']//preceding-sibling::input")).click();
		}
		
		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']//preceding-sibling::input")).isSelected());
		sleepInSecond(1);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	//file này e cũng code
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
