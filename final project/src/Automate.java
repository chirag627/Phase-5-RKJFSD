
import org.testng.annotations.Test;

import io.opentelemetry.exporter.logging.SystemOutLogExporter;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class Automate {
	public WebDriver driver;

	@Parameters("browser")
	@BeforeClass
	public void beforeTest(String browser) {
		System.out.println("Loading.. Please Wait");

		// for firefox
		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\server\\geckodriver.exe");
			driver = new FirefoxDriver();

		}

		// for chrome
		else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\server\\chromedriver.exe");
			driver = new ChromeDriver();

		}

		// for edge
		else if (browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", "C:\\server\\msedgedriver.exe");
			driver = new EdgeDriver();

		}

		System.out.println("Opening " + browser + " for automated testing");
		System.out.println("Going to flipkart.com...");
		driver.manage().window().maximize();

	}

	@Test(priority = 1)
	public void getLoadingTIme() throws InterruptedException {
		System.out.println("\n\n Calculating The page load time");
		System.out.println("-------------------------------------");

		long start = System.currentTimeMillis();
		driver.get("https://flipkart.com");
		long finish = System.currentTimeMillis();
		long totalTime = finish - start;

		System.out.println("\nPage Load TIme: " + totalTime + "\n\n");

		driver.findElement(By.xpath("//button[contains(.,'✕')]")).click();
		System.out.println("\n\nClosing login modal...");
	}

	@Test(priority = 2)
	public void checkImages() throws InterruptedException {

		System.out.println("Checking If Images are loaded or not");

		driver.findElement(By.name("q")).sendKeys("iphone 13");
		driver.findElement(By.className("L0Z3Pu")).click();

		Thread.sleep(3000);
		List<WebElement> i = driver.findElements(By.xpath("//img[@class='_396cs4 _3exPp9']"));
		for (WebElement element : i) {
			long start = System.currentTimeMillis();
			Boolean check = (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete "
					+ "&& typeof arguments[0].naturalWidth != \"undefined\" " + "&& arguments[0].naturalWidth > 0",
					element);
			long finish = System.currentTimeMillis();

			if (check) {
				System.out.println("Images is  loaded in " + (finish - start) + "s");
			} else {
				System.out.println("Images are not loaded");
			}
		}
	}

	@Test(priority = 3)
	public void scrollPage() {

		driver.get(
				"https://www.flipkart.com/apple-iphone-13-midnight-128-gb/p/itmca361aab1c5b0?pid=MOBG6VF5Q82T3XRS&lid=LSTMOBG6VF5Q82T3XRSOXJLM9&marketplace=FLIPKART&q=iphone+13&store=tyy%2F4io&srno=s_1_1&otracker=search&otracker1=search&fm=organic&iid=a2b529bf-6e47-4950-b0e3-33ca14d03be1.MOBG6VF5Q82T3XRS.SEARCH&ppt=hp&ppn=homepage&ssid=2dpx9e0d7k0000001665379065155&qH=c68a3b83214bb235\"");

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scrollBy(0, 2500)");

		try {
			long lastHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");

			while (true) {
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
				Thread.sleep(2000);

				long newHeight = (long) ((JavascriptExecutor) driver)
						.executeScript("return document.body.scrollHeight");
				if (newHeight == lastHeight) {
					break;
				}
				lastHeight = newHeight;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Reahed to the bottom");
	}

	@AfterClass
	public void afterTest() {
		driver.quit();
	}
}