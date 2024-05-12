package pages;

import constants.locators.SearchPageConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchResultsPage extends BasePage{

    private By statusCheck = By.className(SearchPageConstants.STATUSCHECK);
    private By products = By.cssSelector(SearchPageConstants.ADDTOCARTBUTTON1);
    private By statusCheck2 = By.xpath(SearchPageConstants.STATUSCHECK2);
    private By productTitle = By.className(SearchPageConstants.PRODUCTTITLE);
    private By sortButton = By.id(SearchPageConstants.SORTBY);
    private By lowToHighButton = By.cssSelector(SearchPageConstants.LOWTOHIGH);//this one
    private By priceTexts = By.className(SearchPageConstants.PRICETEXTS);
    private By prodCount = By.id(SearchPageConstants.PRODUCTCOUNT);

    public SearchResultsPage(WebDriver driver){
        super(driver);
    }

    public String getStatusText(){
        return getDriver().findElement(statusCheck).getText();
    }
    public String getErrorStatusText(){
        return getDriver().findElement(statusCheck2).getText();
    }

    public void addToCart(){
        WebElement e = getDriver().findElement(products);
        Actions actions = new Actions(getDriver());
        actions.moveToElement(e).click().perform();
    }
    public ExpectedCondition<WebElement> isTitleVisible(){
        return ExpectedConditions.visibilityOfElementLocated(productTitle);
    }
    public String getCartItem(){
        return getDriver().findElements(productTitle).get(0).getText();
    }
    public void click_sortBy_button(){
        getDriver().findElement(sortButton).click();
    }

    public void select_lowtohigh_option() throws InterruptedException {
        click_sortBy_button();
        Thread.sleep(3000);
        WebElement dropdownElement = getDriver().findElement(lowToHighButton);
//        Select select = new Select(dropdownElement);
//        select.selectByIndex(1);

        String script = "var elementToHover = document.querySelector('#SortBy > option:nth-child(2)');"
                + "var event = new MouseEvent('mouseover', {"
                + "    'view': window,"
                + "    'bubbles': true,"
                + "    'cancelable': true"
                + "});"
                + "elementToHover.dispatchEvent(event);";

        ((JavascriptExecutor) getDriver()).executeScript(script);
        Thread.sleep(3000);
        dropdownElement.click();
//        String script = "document.querySelector(\"#SortBy > option:nth-child(2)\").click();";
//        ((JavascriptExecutor) getDriver()).executeScript(script);

    }
    public void switchSortToLtH(){
        click_sortBy_button();
//        select_lowtohigh_option();
    }
    public int getNumOfResults(){
        String e1 = getDriver().findElement(prodCount).getText();
        return Integer.parseInt(e1.substring(0, e1.length()-8));
    }
    public double[] getPrices(){
        List<WebElement> e =  getDriver().findElements(priceTexts);
        double[] prices = new double[getNumOfResults()];

        for(int i = 0;i < e.size();i++){
            prices[i] = Double.parseDouble(e.get(i).getText().substring(1));
            System.out.println(e.get(i).getText());
        }


        return prices;
    }
}
