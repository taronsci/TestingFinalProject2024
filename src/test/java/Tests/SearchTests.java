package Tests;

import org.junit.Test;
import org.openqa.selenium.By;
import pages.SearchResultsPage;
import static org.junit.Assert.*;
import constants.AssertionMessages;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class SearchTests extends BaseTest {

    @Test
    public void CocokindValidSearch(){
        homePage.setSearch("cream");
        SearchResultsPage searchResultsPage = homePage.click_search_button();
        assertEquals(AssertionMessages.CHECK_TEXT_INCORRECT, "search results", searchResultsPage.getAlertText());
    }

    @Test
    public void CocokindAddToCart(){
        homePage.setSearch("resurrection polypeptide cream");
        SearchResultsPage searchResultsPage = homePage.click_search_button();
        searchResultsPage.addToCart();

        WebDriverWait wait = new WebDriverWait(searchResultsPage.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultsPage.productTitle));

        assertEquals(AssertionMessages.CHECK_TEXT_INCORRECT, "resurrection polypeptide cream", searchResultsPage.getCartItem());
    }
}
