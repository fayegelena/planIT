package testCases;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import testPages.CartPage;
import testPages.SeleniumDriver;
import testPages.ShoppingPage;

public class TC3 {
	private static WebDriver    driver;
	private static ShoppingPage shoppingPage;
	private static CartPage     cartPage;

	@BeforeAll
	static void setUp() {
		driver       = SeleniumDriver.getDriver();
		shoppingPage = new ShoppingPage(driver);
		cartPage     = new CartPage(driver);
	}

	@AfterAll
	static void tearDown() {
		SeleniumDriver.quitDriver();
	}

	@Test
	void testCart() {
		shoppingPage.navigateToShop();
		shoppingPage.addProductToCart("Stuffed Frog",   2);
		shoppingPage.addProductToCart("Fluffy Bunny",   5);
		shoppingPage.addProductToCart("Valentine Bear", 3);

		cartPage.navigateToCart();

		for (String product : new String[]{
				"Stuffed Frog", "Fluffy Bunny", "Valentine Bear"}) {
			assertTrue(
					cartPage.isProductSubtotalCorrect(product),
					"Subtotal mismatch for " + product
					);
		}

		assertTrue(
				cartPage.isTotalCorrect(),
				"Overall total does not match sum of subtotals"
				);
	}
}
