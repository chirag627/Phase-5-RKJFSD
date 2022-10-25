package automate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Main {
	static WebDriver webdriver;
	boolean isLoogedIn;

	@BeforeClass
	public static void setup() {

		System.setProperty("webdriver.chrome.driver", "C:\\server\\chromedriver.exe");
		webdriver = new ChromeDriver();

	}

	@Test
	public void Registration() {
		System.out.println("Trying to Register a new Customer");
		webdriver.get("https://www.ecomdeveloper.com/demo/index.php?route=account/register");
		webdriver.findElement(By.id("input-firstname")).sendKeys("Ram");
		webdriver.findElement(By.id("input-lastname")).sendKeys("lal");
		webdriver.findElement(By.id("input-email")).sendKeys("Ram22@shayam.com");
		webdriver.findElement(By.id("input-telephone")).sendKeys("0000000000");
		webdriver.findElement(By.id("input-password")).sendKeys("12341234");
		webdriver.findElement(By.id("input-confirm")).sendKeys("12341234");
		webdriver.findElement(By.name("agree")).click();
		webdriver.findElement(By.cssSelector("input[type='submit']")).click();
		try {
			if (webdriver.findElement(By.className("close")).isDisplayed()) {
				System.out.println("\n***************************************");
				System.out.println("User Already registered");
				System.out.println("***************************************\n");

			} else {
				RegConfirm();

			}
		} catch (Exception e) {
			RegConfirm();

		}
	}

	public void RegConfirm() {
		webdriver.get("https://www.ecomdeveloper.com/demo/index.php?route=account/logout");
		webdriver.get("https://www.ecomdeveloper.com/demo/index.php?route=account/login");
		System.out.println("User successfully registered");
	}

	@Test(priority = 2, dependsOnMethods = { "Registration" })
	public void Login() {
		System.out.println("Trying to login");
		webdriver.get("https://www.ecomdeveloper.com/demo/index.php?route=account/login");
		webdriver.findElement(By.id("input-email")).sendKeys("Ram22@shayam.com");
		webdriver.findElement(By.id("input-password")).sendKeys("12341234");
		webdriver.findElement(By.cssSelector("input[type='submit']")).click();
		try {
			if (webdriver.findElement(By.className("alert-dismissible")).isDisplayed()) {
				System.out.println(
						"\n*********************************\nInvalid Credentials\n*********************************\n");
				isLoogedIn = false;

			} else {
				System.out.println(
						"\n*********************************\nUser Successfully LoggedIn\n*********************************\n");
				isLoogedIn = true;
			}
		} catch (Exception e) {
			System.out.println(
					"\n*********************************\nUser Successfully LoggedIn\n*********************************\n");
			isLoogedIn = true;
		}

	}

	@Test(priority = 3)
	public void search() throws InterruptedException {
		System.out.println("Searching product");
		if (isLoogedIn) {
			webdriver.findElement(By.id("search_toggle")).click();
			Thread.sleep(4000);

			webdriver.findElement(By.cssSelector("input[type='text']")).sendKeys("Kurta");
			Thread.sleep(4000);

			webdriver.findElement(By.xpath("//*[@id=\"search_block_top\"]/div/div[1]/button")).click();

		} else {
			System.out.println(
					"\n*********************************\nCannot Search the product as user is not loggedin\n*********************************\n");
		}
	}

	@Test(priority = 4)
	public void cart() throws InterruptedException {
		System.out.println("Adding product to the cart");
		if (isLoogedIn) {

			webdriver.get("https://www.ecomdeveloper.com/demo/index.php?route=product/product&product_id=55");
			webdriver.findElement(By.xpath("//*[@id=\"input-option240\"]/div[1]/label/img")).click();

			Select drpCountry = new Select(webdriver.findElement(By.name("option[241]")));
			drpCountry.selectByVisibleText("S");
			webdriver.findElement(By.id("button-cart")).click();

			webdriver.get("https://www.ecomdeveloper.com/demo/index.php?route=checkout/cart");

		} else {
			System.out.println(
					"\n*********************************\nCannot add product to cart as user is not loggedin\n*********************************\n");
		}
	}

}
