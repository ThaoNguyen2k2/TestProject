package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Part_3 {
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
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		selectedItemDropdown("//span[@id='speed-button']", "//ul[@id='speed-menu']//div[@role='option']", "Fast");
		sleepInSecond(3);
		Assert.assertEquals(
				driver.findElement(By.xpath("//span[@id='speed-button']//span[@class='ui-selectmenu-text']")).getText(),
				"Fast");

		selectedItemDropdown("//span[@id='speed-button']", "//ul[@id='speed-menu']//div[@role='option']", "Faster");
		sleepInSecond(3);
		Assert.assertEquals(
				driver.findElement(By.xpath("//span[@id='speed-button']//span[@class='ui-selectmenu-text']")).getText(),
				"Faster");

		selectedItemDropdown("//span[@id='speed-button']", "//ul[@id='speed-menu']//div[@role='option']", "Slow");
		sleepInSecond(3);
		Assert.assertEquals(
				driver.findElement(By.xpath("//span[@id='speed-button']//span[@class='ui-selectmenu-text']")).getText(),
				"Slow");

	}

	// @Test
	public void TC_02_ReactJS() {

		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		selectedItemDropdown("//i[@class='dropdown icon']", "//span", "Elliot Fu");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Elliot Fu");

		selectedItemDropdown("//i[@class='dropdown icon']", "//span", "Jenny Hess");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Jenny Hess");
	}

	// @Test
	public void TC_03_VueJS() {

		driver.get("https://mikerodham.github.io/vue-dropdowns/");

		selectedItemDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "First Option");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "First Option");

		selectedItemDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "Second Option");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Second Option");
	}

	 @Test
	public void TC_04_Editable() {

		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		
		selectedItemDropdown("//input[@class='search']", "//div[@role='option']//span", "Albania");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Albania");

		selectedItemDropdown("//input[@class='search']", "//div[@role='option']//span", "Belarus");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Belarus");
		
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

	public void selectedItemDropdown(String ParentXpath, String allItemXpath, String expectedTextItem) {
//			Click vào 1 thẻ bất kì sao cho xem hết các item của dropdown 
		driver.findElement(By.xpath(ParentXpath)).click();

//			Chờ tất các item load thành công
//			Lấy locator đại diện cho tất cả item
//			Lấy đến thẻ chứ text
		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));

		List<WebElement> speedDropdownItem = driver.findElements(By.xpath(allItemXpath));

//			Tìm xem item đúng cái cần tìm không 		
//			Nếu nó nằm trong khoảng nhìn thấy -> không cần scroll xuống
//			Nếu không nhìn thấy -> scroll xuống đến item đó
//			Kiểm tra text của item đúng với giá trị mong muốn
//			Click và item đó
		for (WebElement tempItem : speedDropdownItem) {
			String itemText = tempItem.getText();

			if (itemText.trim().equals(expectedTextItem)) {
				tempItem.click();
				break;
			}
		}
	}

	public void EnterAndSelectedItemDropdown(String textBoxXpath, String allItemXpath, String expectedTextItem) {

		driver.findElement(By.xpath(textBoxXpath)).clear();

		driver.findElement(By.xpath(textBoxXpath)).sendKeys(expectedTextItem);
		sleepInSecond(3);
		
		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));

		List<WebElement> speedDropdownItem = driver.findElements(By.xpath(allItemXpath));

		for (WebElement tempItem : speedDropdownItem) {
			if (tempItem.getText().trim().equals(expectedTextItem)) {
				tempItem.click();
				break;
			}
		}

	}

}
