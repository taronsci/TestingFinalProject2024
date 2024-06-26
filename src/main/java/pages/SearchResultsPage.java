package pages;

import constants.locators.SearchPageConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class SearchResultsPage extends BasePage{

    private By statusCheck = By.className(SearchPageConstants.STATUSCHECK);
    private By products = By.cssSelector(SearchPageConstants.ADDTOCARTBUTTON1);
    private By soldOutProd = By.cssSelector(SearchPageConstants.ADDTOCARTBUTTON2);
    private By statusCheck2 = By.xpath(SearchPageConstants.STATUSCHECK2);
    private By productTitle = By.className(SearchPageConstants.PRODUCTTITLE);
    private By sortButton = By.id(SearchPageConstants.SORTBY);
    private By lowToHighButton = By.cssSelector(SearchPageConstants.LOWTOHIGH);
    private By priceTexts = By.className(SearchPageConstants.PRICETEXTS);
    private By prodCount = By.id(SearchPageConstants.PRODUCTCOUNT);
    private By yourCart = By.cssSelector(SearchPageConstants.YOURCART);
    private By plus = By.cssSelector(SearchPageConstants.CARTCOUNTPLUS);
    private By minus = By.cssSelector(SearchPageConstants.CARTCOUNTMINUS);
    private By amount = By.className(SearchPageConstants.AMOUNT);
    private By cartCount = By.className(SearchPageConstants.ITEMSINCART);
    private By dialog = By.className(SearchPageConstants.DIALOG);
    private int resultPerPage = 24;

    public SearchResultsPage(WebDriver driver){
        super(driver);
    }
    public String getStatusText(){
        return getDriver().findElement(statusCheck).getText();
    }
    public String getErrorStatusText(){
        return getDriver().findElement(statusCheck2).getText();
    }
    public void setResultPerPage(int resultPerPage){
        this.resultPerPage = resultPerPage;
    }
    public void addToCart(){
        WebElement e = getDriver().findElement(products);
        Actions actions = new Actions(getDriver());
        actions.moveToElement(e).click().perform();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(isTitleVisible());
    }
    public void addToCartSO(){
        WebElement e = getDriver().findElement(soldOutProd);
        Actions actions = new Actions(getDriver());
        actions.moveToElement(e).click().perform();
    }
    public ExpectedCondition<WebElement> isTitleVisible(){
        return ExpectedConditions.visibilityOfElementLocated(productTitle);
    }
    public String getCartItem(){
        return getDriver().findElements(productTitle).get(0).getText();
    }
    public double[] getPrices(){
        List<WebElement> e =  getDriver().findElements(priceTexts);
        double[] prices = new double[resultPerPage];

        for(int i = 0;i < prices.length; i++){
            String s = e.get(i).getText();
            if(s.length() != 0){
                if(s.charAt(1) == '0')
                    continue;

                if (s.contains(" "))
                    prices[i] = Double.parseDouble(e.get(i).getText().substring(1, e.get(i).getText().indexOf(" ")));
                else
                    prices[i] = Double.parseDouble(e.get(i).getText().substring(1));
            }
        }
        return prices;
    }
    public static boolean isSorted(double[] arr){
        int left = 0, n = arr.length, right = n - 1;
        while (left < right) {
            if (arr[left] > arr[right]) {
                return false;
            }
            else {
                if (left != 0 && right != n - 1 && (arr[left] < arr[left - 1] || arr[right] > arr[right + 1])) {
                    return false;
                }
            }
            left++;
            right--;
        }
        return true;
    }
    public boolean isCartPresent() {
        try {
            getDriver().findElement(yourCart);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
    public void click_plus(){
        getDriver().findElement(plus).click();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until((ExpectedCondition<Boolean>) driver -> {
            WebElement button = getDriver().findElement(amount);
            String enabled = button.getAttribute("value");
            return !enabled.equals("1");
        });
    }
    public void click_minus(){
        getDriver().findElement(minus).click();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until((ExpectedCondition<Boolean>) driver -> {
            String enabled = getCartCount();
            return !enabled.equals("1");
        });
    }
    public void setItemCount(String count){
        WebElement e = getDriver().findElement(amount);
//        e.sendKeys(Keys.BACK_SPACE);
        e.sendKeys(count);
        click_plus();
    }
    public String getCartCount() {
        WebElement e = getDriver().findElement(cartCount);
        return e.getText().substring(2, e.getText().indexOf(" i"));
    }
    public String getAmount(){
        return getDriver().findElement(amount).getAttribute("value");
    }
    public boolean isDialogDisplayed(){
        return getDriver().findElement(dialog).isDisplayed();
    }
    public ExpectedCondition<WebElement> isDialogVisible(){
        return ExpectedConditions.visibilityOfElementLocated(dialog);
    }
    public void waits(){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(isDialogVisible());
    }
    public void click_sortBy_button(){
        getDriver().findElement(sortButton).click();
    }
    public void select_lowtohigh_option() {
        WebElement dropdownElement = getDriver().findElement(lowToHighButton);
        dropdownElement.click();
    }
    public void switchSortToLtH(){
        click_sortBy_button();
        select_lowtohigh_option();
    }
    public int getNumOfResults(){
        String e1 = getDriver().findElement(prodCount).getText();
        return Integer.parseInt(e1.substring(0, e1.length()-8));
    }
}
