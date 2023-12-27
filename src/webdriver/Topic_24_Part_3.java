package webdriver;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_24_Part_3 {
	WebDriver driver;
	Alert alert;
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
		driver.manage().window().maximize();
		expliciWait = new WebDriverWait(driver, 10);
	}

	//@Test
	public void TC_01_FindElement() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		//Tìm thấy duy nhất 1 element/node
		//Tìm thấy và Thao tác trực tiếp lên node đó -> không cần phải chờ hết thời gian timeout
		driver.findElement(By.cssSelector("input#email"));
		//Tìm thấy nhiều hơn 1 element/node -> Thao tác với node đầu tiên
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("dam@gmail.com");
		
		//Không tìm thấy element/node nào -> nửa giây tìm lại 1 lần
		//Nếu hết time mà vẫn không tìm thấy -> fail testcase, throw 1 exception
	}

	@Test
	public void TC_02_FindElements() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		//Tìm thấy duy nhất 1 element/node
		//Tìm thấy nhiều hơn 1 element/node
		List <WebElement> elements = driver.findElements(By.cssSelector("input#email"));
		System.out.println(elements.size());
		
		elements = driver.findElements(By.cssSelector("input"));
		System.out.println(elements.size());
		
		//Không tìm thấy element/node nào
		//Nếu hết time vẫn không tìm thấy -> không đánh fail, vẫn chạy step tiếp theo, trả về 1 list rỗng
		
		
		
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
