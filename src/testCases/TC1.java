package testCases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.WebDriver;

import testPages.ContactPage;
import testPages.Homepage;
import testPages.SeleniumDriver;

@TestMethodOrder(OrderAnnotation.class)
public class TC1 {

	private static WebDriver driver;
	private static Homepage homePage;
	private static ContactPage contactPage;

	@BeforeAll
	static void setUpBeforeClass() {
		driver = SeleniumDriver.getDriver();
		driver.get("http://jupiter.cloud.planittesting.com");
		homePage = new Homepage(driver);
		contactPage = new ContactPage(driver);
	}

	@AfterAll
	static void tearDownAfterClass() {
		SeleniumDriver.quitDriver();
	}

	@Test
	@Order(1)
	void navigateToHomepage() {
		assertTrue(homePage.isWelcomeTextDisplayed("Welcome to Jupiter Toys"),
				"Welcome to Jupiter Toys text was not found on the page!");
	}

	@Test
	@Order(2)
	void navigateToContactPage() {
		homePage.clickContact();
	}

	@Test
	@Order(3)
	void clickSubmitButton() {
		contactPage.clickSubmit();
	}

	@Test
	@Order(4)
	void validateErrorMsg() {
		assertEquals("Forename is required", contactPage.getForenameErrorText(), "Forename error message displayed");
		assertEquals("Email is required", contactPage.getEmailErrorText(), "Email error message displayed");
		assertEquals("Message is required", contactPage.getMessageErrorText(), "Message error message displayed");
	}

	@Test
	@Order(5)
	void populateAndSubmitMandatoryFields() {
		contactPage.populateContactFields("PlanIT", "planit@test.com", "Planit Test Only");
	}

	@Test
	@Order(6)
	void validateErrorMsgGone() {
		assertTrue(contactPage.isErrMSgGone(""),
				"No error message was not found on the page!");
	}
}
