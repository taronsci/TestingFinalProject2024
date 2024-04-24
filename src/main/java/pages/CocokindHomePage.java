package pages;

import constants.locators.CocokindHomePageConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CocokindHomePage {

    private static WebElement element = null;

    public static WebElement search_button(WebDriver driver){
        element = driver.findElement(By.xpath(CocokindHomePageConstants.SEARCHBUTTON));
        return element;
    }

    public static WebElement search_field(WebDriver driver){
        element = driver.findElement(By.xpath(CocokindHomePageConstants.SEARCHFIELD));
        return element;
    }
    public static WebElement close_Ad(WebDriver driver){
        //element = driver.findElement(By.cssSelector(CocokindHomePageConstants.CLOSEADD));
        return element = driver.findElement(By.xpath("/html/body/div[14]//div/div/form[7]/div/button"));
    }
    public static WebElement search(WebDriver driver){
        //element = driver.findElement(By.cssSelector(CocokindHomePageConstants.CLOSEADD));
        //element = driver.findElement(By.xpath("/html/body/div[14]//div/div/form[7]/div/button"));

        element = search_button(driver);
        element = search_field(driver);
        return element;
    }

}
