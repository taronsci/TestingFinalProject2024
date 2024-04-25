package Tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CocokindHomePage;

public abstract class BaseTest{

    private static WebDriver driver;
    protected static CocokindHomePage homePage;
    public static final String LINK = "https://www.cocokind.com";

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

}
