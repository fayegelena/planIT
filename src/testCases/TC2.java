package testCases;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.WebDriver;

import testPages.ContactPage;
import testPages.Homepage;
import testPages.SeleniumDriver;

@TestMethodOrder(OrderAnnotation.class)
public class TC2 {

	private WebDriver driver;
	private Homepage homePage;
	private ContactPage contactPage;

	@BeforeEach
	void setUp() {
		driver = SeleniumDriver.getDriver();
		driver.get("http://jupiter.cloud.planittesting.com");
		homePage = new Homepage(driver);
		contactPage = new ContactPage(driver);
	}

	@AfterEach
	void tearDown() {
		SeleniumDriver.quitDriver();
	}

	void navigateToHomepage() {
		assertTrue(homePage.isWelcomeTextDisplayed("Welcome to Jupiter Toys"),
				"Welcome to Jupiter Toys text was not found on the page!");
	}

	void navigateToContactPage() {
		homePage.clickContact();
	}

	void populateAndSubmitMandatoryFields() {
		contactPage.waitElement("forename");
		contactPage.populateContactFields("PlanIT", "planit@test.com", "Planit Test Only");
	}

	void clickSubmitButton() {
		contactPage.clickSubmit();
	}

	void validateSuccessfullMsg() {
		assertTrue(contactPage.isFeedbackSuccessful("Thanks"),
				"Thanks txt was not found on the page!");
	}

	@RepeatedTest(5)
	@Order(1)
	void executeFullFlow() {
		navigateToHomepage();
		navigateToContactPage();
		populateAndSubmitMandatoryFields();
		clickSubmitButton();
		validateSuccessfullMsg();
	}
}
