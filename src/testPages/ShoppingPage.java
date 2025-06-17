package testPages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShoppingPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public ShoppingPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToShop() {
        driver.get("https://jupiter.cloud.planittesting.com/#/shop");
    }

    public void addProductToCart(String productName, int quantity) {
        String xpath = "//h4[text()='" + productName + "']"
                     + "/following-sibling::p//a[contains(@class,'btn-success')]";
        for (int i = 0; i < quantity; i++) {
            WebElement btn = wait
              .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            ((JavascriptExecutor) driver)
              .executeScript("arguments[0].scrollIntoView(true);", btn);
            wait.until(ExpectedConditions.elementToBeClickable(btn)).click();
        }
    }
}
