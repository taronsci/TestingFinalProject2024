package pages;

import constants.locators.CheckoutPageConstants;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage{
    public By statusCheck = By.id(CheckoutPageConstants.STATUSCHECK);
    private By firstname = By.id(CheckoutPageConstants.FIRSTNAME);
    private By lastname = By.id(CheckoutPageConstants.LASTNAME);
    private By address = By.id(CheckoutPageConstants.ADDRESS);
    private By warning = By.id(CheckoutPageConstants.EMAILWARNINGMESSAGE);
    private By payNow = By.cssSelector(CheckoutPageConstants.PAYNOW);


    public CheckoutPage(WebDriver driver) {
        super(driver);
    }
    public boolean getStatus(){
        return getDriver().findElement(statusCheck).isDisplayed();
    }
    public boolean getEmailWarning(){
        return getDriver().findElement(warning).isDisplayed();
    }
    public void setFirstname(String firstname){
        getDriver().findElement(this.firstname).sendKeys(firstname);
    }
    public void setLastname(String lastname){
        getDriver().findElement(this.lastname).sendKeys(lastname);
    }
    public void setAddress(String address){
        getDriver().findElement(this.address).sendKeys(address);
    }
    public void click_PayNow_button(){
        getDriver().findElement(payNow).click();
    }

}
