package testPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Homepage {

	private WebDriver driver;
	private By bodyTxt = By.tagName("body");
	private By contactLink = By.linkText("Contact");

	public Homepage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isWelcomeTextDisplayed(String text) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.textToBePresentInElementLocated(bodyTxt, text));
	}

	public void clickContact() {
		driver.findElement(contactLink).click();
	}

}
