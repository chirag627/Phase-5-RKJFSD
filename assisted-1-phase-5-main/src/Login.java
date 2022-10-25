
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {
	public static void main(String[] args) {

		WebDriver webdriver = null;

		System.setProperty("webdriver.chrome.driver", "C:\\server\\chromedriver.exe");
		webdriver = new ChromeDriver();

		webdriver.manage().window().maximize();
		webdriver.get("https://www.hackerrank.com/auth/login");

		webdriver.findElement(By.name("username")).sendKeys("svs195794@xcoxc.com");
		webdriver.findElement(By.name("password")).sendKeys("Ram@12341234");
		webdriver.findElement(By.className("auth-button")).click();

	}
}