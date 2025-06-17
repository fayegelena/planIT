package testPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumDriver {

	private static WebDriver driver;

	public static WebDriver getDriver() {
		if(driver == null) {
			System.setProperty("webdriver.chrome.driver", "C:\\WebDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		return driver;
	}

	public static void quitDriver() {
		if(driver != null) {
			driver.quit();
			driver = null;
		}
	}

}

