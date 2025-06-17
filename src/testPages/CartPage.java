package testPages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final double DELTA = 0.01;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void navigateToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("#nav-cart > a"))).click();
    }

    public boolean isProductSubtotalCorrect(String productName) {
        WebElement row = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//table[contains(@class,'table')]/tbody"
                     + "/tr[td[1][normalize-space(text())='"
                     + productName + "']]")
        ));

        List<WebElement> cells = row.findElements(By.tagName("td"));

        double price = Double.parseDouble(
            cells.get(1).getText().replace("$", "").trim()
        );
        int quantity = Integer.parseInt(
            cells.get(2).findElement(By.tagName("input"))
                 .getAttribute("value").trim()
        );
        double subtotal = Double.parseDouble(
            cells.get(3).getText().replace("$", "").trim()
        );

        double expected = price * quantity;
        System.out.printf("%s → $%.2f × %d = $%.2f (found $%.2f)%n",
            productName, price, quantity, expected, subtotal);

        return Math.abs(expected - subtotal) < DELTA;
    }

    public boolean isTotalCorrect() {
        try {

            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                By.cssSelector("table.table tbody tr"), 0));

            List<WebElement> rows = driver.findElements(
                By.cssSelector("table.table tbody tr")
            );
            double sum = 0;
            for (WebElement row : rows) {
                List<WebElement> tds = row.findElements(By.tagName("td"));
                if (tds.size() < 4) continue;
                sum += Double.parseDouble(
                    tds.get(3).getText().replace("$", "").trim()
                );
            }
            WebElement totalCell = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//table[contains(@class,'table')]/tfoot//td[last()]")
            ));
            double displayed = Double.parseDouble(
                totalCell.getText().replace("Total: ", "").replace("$", "").trim()
            );

            System.out.printf("Computed total: $%.2f ‖ Displayed total: $%.2f%n",
                sum, displayed);
            return Math.abs(sum - displayed) < DELTA;
        } catch (Exception e) {
            System.out.println("Error verifying total: " + e.getMessage());
            return false;
        }
    }
}
