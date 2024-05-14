package Tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CocokindHomePage;

public abstract class BaseTest{

    protected static WebDriver driver;
    protected static CocokindHomePage homePage;
    public static final String LINK = "https://www.cocokind.com";
    public static final String LINKLtH = "https://www.cocokind.com/search?q=cream&options%5Bprefix%5D=last&sort_by=price-ascending";

    @BeforeClass
    public static void setUp(){
        driver = new ChromeDriver();
        driver.get(LINK);

        homePage = new CocokindHomePage(driver);
        homePage.close_Ad();
    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }

    public WebDriver getDriver(){
        return driver;
    }
}
