import constants.locators.CocokindHomePageConstants;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CocokindHomePage;
import java.time.Duration;

public class BaseTest {
    private WebDriver driver;

    public static final String LINK = "https://www.cocokind.com";

    public BaseTest(){
        driver = new ChromeDriver();
    }
    public static void main(String[] args) {

    }

    @Test
    public void CocokindSearch() throws InterruptedException {
        BaseTest test = new BaseTest();

        test.driver.get(LINK);

        WebElement revealed = CocokindHomePage.close_Ad(test.driver);
        Wait<WebDriver> wait = new WebDriverWait(test.driver, Duration.ofSeconds(10));
        wait.until(d -> revealed.isDisplayed());
        revealed.click();
        CocokindHomePage.search(driver).sendKeys("cream");
        test.driver.findElement(By.name("btnK")).sendKeys(Keys.RETURN);
        test.driver.quit();
    }
}
