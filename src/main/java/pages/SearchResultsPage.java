package pages;

import constants.locators.SearchPageConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class SearchResultsPage extends BasePage{
    private By statusCheck = By.className(SearchPageConstants.STATUSCHECK);
    private By products = By.cssSelector(SearchPageConstants.ADDTOCARTBUTTON1);

    public By productTitle = By.className(SearchPageConstants.PRODUCTTITLE);

    public SearchResultsPage(WebDriver driver){
        super(driver);
    }

    public String getStatusText(){
        return getDriver().findElement(statusCheck).getText();
    }

    public void addToCart(){
        WebElement e = getDriver().findElement(products);
        Actions actions = new Actions(getDriver());
        actions.moveToElement(e).click().perform();
    }

    public String getCartItem(){
        return getDriver().findElements(productTitle).get(0).getText();
    }

}
