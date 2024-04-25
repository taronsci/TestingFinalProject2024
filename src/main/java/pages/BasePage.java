package pages;

import constants.locators.SearchPageConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    private WebDriver driver;
    private By checkoutButton = By.cssSelector(SearchPageConstants.CHECKOUT);

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public CheckoutPage click_checkout_button(){
        getDriver().findElement(checkoutButton).click();
        return new CheckoutPage(getDriver());
    }
}
