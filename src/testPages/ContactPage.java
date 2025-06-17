package testPages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactPage {

	private WebDriver driver;
	private By submitButton = By.linkText("Submit");
	private By forenameError = By.id("forename-err");
	private By emailError = By.id("email-err");
	private By messageError = By.id("message-err");
	private By bodyTxt = By.tagName("body");

	public ContactPage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickSubmit() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
	}

	public String getForenameErrorText() {
		return driver.findElement(forenameError).getText();
	}

	public String getEmailErrorText() {
		return driver.findElement(emailError).getText();
	}

	public String getMessageErrorText() {
		return driver.findElement(messageError).getText();
	}

	public void populateContactFields(String forename, String email, String message) {
		driver.findElement(By.id("forename")).sendKeys(forename);
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("message")).sendKeys(message);
	}

	public boolean isErrMSgGone(String text) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		boolean forenameGone = wait.until(ExpectedConditions.invisibilityOfElementLocated(forenameError));
		boolean emailGone = wait.until(ExpectedConditions.invisibilityOfElementLocated(emailError));
		boolean messageGone = wait.until(ExpectedConditions.invisibilityOfElementLocated(messageError));
		return forenameGone && emailGone && messageGone;
	}

	public boolean isFeedbackSuccessful(String text) { WebDriverWait wait = new
			WebDriverWait(driver, Duration.ofSeconds(10));
	return wait.until(ExpectedConditions.textToBePresentInElementLocated(bodyTxt,
			text)); 
	}

	public void waitElement(String text) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(text)));
	}
}
