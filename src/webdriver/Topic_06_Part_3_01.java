package webdriver;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Part_3_01 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random rand = new Random();
    String employeeID = String.valueOf(rand.nextInt(999999));
    String passportNumber = "40517-402-96-7202";
    String comment = "Not fuond\n"
    		+ "web";
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_Create_New_Employee() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");	
		driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
		sleepInSecond(5);
		
		driver.findElement(By.xpath("//span[text() = 'PIM']/ancestor::a")).click();		
		driver.findElement(By.xpath("//a[text() = 'Add Employee']")).click();
		
		driver.findElement(By.name("firstName")).sendKeys("VTI");	
		driver.findElement(By.name("middleName")).sendKeys("Test");	
		driver.findElement(By.name("lastName")).sendKeys("Automation");	
		driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).sendKeys(Keys.chord(Keys.CONTROL,"a"));
		sleepInSecond(1);
		driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).sendKeys(Keys.DELETE);
		sleepInSecond(2);
		driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).sendKeys(employeeID);
		
		driver.findElement(By.xpath("//p[text()='Create Login Details']/parent::div//span")).click();
		sleepInSecond(3);
		driver.findElement(By.xpath("//label[text()='Username']/parent::div//following-sibling::div/input")).sendKeys("afc" + employeeID);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div//following-sibling::div/input")).sendKeys("Password123!!!");
		driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div//following-sibling::div/input")).sendKeys("Password123!!!");
		
		driver.findElement(By.xpath("//button[text()=' Save ']")).click();
		sleepInSecond(8);
		
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), "VTI");
		Assert.assertEquals(driver.findElement(By.name("middleName")).getAttribute("value"), "Test");
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), "Automation");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div//following-sibling::div/input")).getAttribute("value"), employeeID);
		
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/parent::div/button")).click();
		driver.findElement(By.xpath("//label[text()='Number']/parent::div//following-sibling::div/input")).sendKeys(passportNumber);
		driver.findElement(By.xpath("//label[text()='Comments']/parent::div//following-sibling::div/textarea")).sendKeys(comment);
		driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
		sleepInSecond(6);
		//driver.findElement(By.xpath("//label[text()='Username']/parent::div//following-sibling::div/input")).sendKeys("afc" + employeeID);
		
		driver.findElement(By.xpath("//i[@class='oxd-icon bi-pencil-fill']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div//following-sibling::div/input")).getAttribute("value"), passportNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div//following-sibling::div/textarea")).getAttribute("value"), comment);
		
		//Login
		driver.findElement(By.cssSelector("p.oxd-userdropdown-name")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		driver.findElement(By.name("username")).sendKeys("afc" + employeeID);
		driver.findElement(By.name("password")).sendKeys("Password123!!!");	
		driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
		sleepInSecond(5);
		
		
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), "VTI");
		Assert.assertEquals(driver.findElement(By.name("middleName")).getAttribute("value"), "Test");
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), "Automation");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Username']/parent::div//following-sibling::div/input")).getAttribute("value"), employeeID);
	
		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/parent::div/button")).click();
		sleepInSecond(5);
		
		driver.findElement(By.xpath("//i[@class='oxd-icon bi-pencil-fill']")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div//following-sibling::div/input")).getAttribute("value"), passportNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div//following-sibling::div/textarea")).getAttribute("value"), comment);
	}
	
//	@Test
//	public void TC_02_Verify_Employee() {
//	
//	}
//	
	@AfterClass
	public void afterClass() {
		//driver.quit();
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
