package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Part_3_02 {
		WebDriver driver;
		String projectPath = System.getProperty("user.dir");
		String osName = System.getProperty("os.name");
	
		@BeforeClass
		public void beforeClass() {
			if (osName.contains("Windows")) {
				System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDriver\\chromedriver.exe");
			} else {
				System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/chromedriver");
			}
	
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}
			
		@Test
		public void TC_01_Url() {
			
			driver.get("http://live.techpanda.org/");
			
			//Login
			driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();	
			sleepInSecond(2);
			Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/" );
			
			//Click new account
			driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
			sleepInSecond(2);
			Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");
			
		}
		
		@Test
		public void TC_02_Title() {
			driver.get("http://live.techpanda.org/");
			
			//Login
			driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();	
			sleepInSecond(2);
			Assert.assertEquals(driver.getTitle(),"Customer Login" );
			
			//Click new account
			driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
			sleepInSecond(2);
			Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
			
		}
		
		@Test
		public void TC_03_Navigate() {
			driver.get("http://live.techpanda.org/");
			
			//Login
			driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();	
			sleepInSecond(3);
			//Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/" );
			
			//Click new account
			driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
			sleepInSecond(3);
			//Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");
			
			//Back
			driver.navigate().back();
			sleepInSecond(2);
			Assert.assertEquals(driver.getTitle(),"Customer Login" );
			
			//Forward
			driver.navigate().forward();
			sleepInSecond(2);
			Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
			
		}
		
		@Test
		public void TC_04_Page_Source_HTML() {
			driver.get("http://live.techpanda.org/");
			
			driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();	
			sleepInSecond(2);
			Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
			
			driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
			sleepInSecond(2);
			Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
		}
		
		@AfterClass
		public void afterClass() {
			driver.quit();
		}
		
		public void sleepInSecond(long second) {
			try {
				Thread.sleep(second*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
