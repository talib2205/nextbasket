package StepDefinitions.Browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.*;
import static org.junit.Assert.*;

public class PromoLabelSteps {
    WebDriver driver;

    @Given("I navigate to the test store")
    public void i_navigate_to_the_test_store() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://teststoreforsouthafri.nextbasket.shop/");
    }

    @When("I search for a product with a \"50% Off\" label")
    public void i_search_for_a_product_with_50_percent_off_label() {
        WebElement promoLabel = driver.findElement(By.xpath("//*[contains(text(),'50% Off')]"));
        assertTrue(promoLabel.isDisplayed());
    }

    @Then("I should see the \"50% Off\" label displayed")
    public void i_should_see_the_50_percent_off_label_displayed() {
        WebElement promoLabel = driver.findElement(By.xpath("//*[contains(text(),'50% Off')]"));
        assertTrue(promoLabel.isDisplayed());
        driver.quit();
    }
}

