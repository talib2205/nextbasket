package StepDefinitions.Browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.*;

import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class OrderSteps {
    WebDriver driver;

    @Given("I navigate to the test store")
    public void i_navigate_to_the_test_store() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://teststoreforsouthafri.nextbasket.shop/");
    }

    @When("I search for the first non-promo in-stock product")
    public void i_search_for_the_first_non_promo_in_stock_product() {
        // Find all products
        List<WebElement> products = driver.findElements(By.cssSelector(".product-item"));

        for (WebElement product : products) {
            // Check if the product is in stock and does not have a promo label
            boolean isInStock = product.findElement(By.cssSelector(".stock-status")).getText().contains("In stock");
            boolean isPromo = product.findElements(By.cssSelector(".promo-label")).isEmpty();

            if (isInStock && isPromo) {
                product.click(); // Click on the first valid product
                break;
            }
        }
    }

    @And("I add the product to the cart")
    public void i_add_the_product_to_the_cart() {
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart"));
        addToCartButton.click();
    }

    @And("I proceed to checkout")
    public void i_proceed_to_checkout() {
        driver.findElement(By.id("cart")).click();
        driver.findElement(By.id("proceed-to-checkout")).click();
    }

    @And("I enter shipping details with {string} as country and {string} as city")
    public void i_enter_shipping_details(String country, String city) {
        driver.findElement(By.id("country")).sendKeys(country);
        driver.findElement(By.id("city")).sendKeys(city);
        driver.findElement(By.id("continue-shipping")).click();
    }

    @And("I complete the order")
    public void i_complete_the_order() {
        driver.findElement(By.id("complete-order")).click();
    }

    @Then("I should see the order confirmation")
    public void i_should_see_the_order_confirmation() {
        WebElement confirmationMessage = driver.findElement(By.cssSelector(".confirmation-message"));
        assertTrue(confirmationMessage.isDisplayed());
        driver.quit();
    }
}

