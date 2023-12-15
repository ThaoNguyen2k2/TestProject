package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Part_3_03 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random rand = new Random();
	String emailRand;
	// WebElement element = driver.findElement(By.id(""));

	By emailTextbox = By.id("mail");
	By AgeUnder18Radio = By.id("under_18");
	By eduTextArea = By.id("edu");
	By nameUser5Text = By.xpath("//h5[text()='Name: User5']");

	By password = By.id("disable_password");
	By Bio = By.id("bio");
	By devCheckbox = By.id("development");

	String firstName, middleName, lastName, emailAddress, passwordCreate, confirmPassword, fullName;

	@BeforeClass
	public void beforeClass() {
		//if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDriver\\chromedriver.exe");
		//} else {
			//System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/chromedriver");
		//}

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		//emailRand = "auto" + rand.nextInt(99999) + "@gmail.com";
		firstName = "Thao";
		middleName = "Phuong";
		lastName = "Nguyen";
		emailAddress = "automation" + rand.nextInt(9999) + "@gmail.com";
		passwordCreate = "123456";
		confirmPassword = "123456";
		fullName = firstName + " " + middleName + " " + lastName;
	}

	@Test
	public void TC_01() {

		driver.get("http://live.techpanda.org/index.php/customer/account/login/");

		// Login
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(2);

		driver.findElement(By.id("send2")).click();

		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
	}

	@Test
	public void TC_02() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");

		// Login
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(2);

		driver.findElement(By.id("email")).sendKeys("123434234@12312.123123");
		driver.findElement(By.id("pass")).sendKeys("123456");

		driver.findElement(By.id("send2")).click();

		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(),
				"Please enter a valid email address. For example johndoe@domain.com.");

	}

//	@Test
//	public void TC_03_Selected() {
//		driver.get("https://automationfc.github.io/basic-form/index.html");
//
//	}

	@Test
	public void TC_04() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(2);

		driver.findElement(By.id("email")).sendKeys(emailRand);
		driver.findElement(By.id("pass")).sendKeys("123456");

		driver.findElement(By.id("send2")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(), "Invalid login or password.");
	}

	@Test
	public void TC_05_CreateAccount() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");

		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInSecond(2);

		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("middlename")).sendKeys(middleName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(passwordCreate);
		driver.findElement(By.id("confirmation")).sendKeys(confirmPassword);

		driver.findElement(By.xpath("//button[@title='Register']")).click();
		sleepInSecond(2);

		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
				"Thank you for registering with Main Website Store.");
		// System.out.println("ok");
		String contactInformationText = driver
				.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p"))
				.getText();
		System.out.println(contactInformationText);
		Assert.assertTrue(contactInformationText.contains(fullName));
		Assert.assertTrue(contactInformationText.contains(emailAddress));

		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		sleepInSecond(2);

		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		sleepInSecond(2);

		// Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/");
		Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src,'logo.png')]")).isDisplayed());
	}

	@Test
	public void TC_06_Login() {
		// driver.get("http://live.techpanda.org/index.php/customer/account/login/");

		// Login
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(2);

		driver.findElement(By.id("email")).sendKeys(emailAddress);
		driver.findElement(By.id("pass")).sendKeys(passwordCreate);

		driver.findElement(By.id("send2")).click();

		String contactInformationText = driver
				.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p"))
				.getText();
		System.out.println(contactInformationText);
		Assert.assertTrue(contactInformationText.contains(fullName));
		Assert.assertTrue(contactInformationText.contains(emailAddress));
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
