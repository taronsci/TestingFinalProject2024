package Tests;

import constants.AssertionMessages;
import org.junit.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SearchResultsPage;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CartTests extends BaseTest{
    @Test
    public void CocokindValidAddToCart(){
        homePage.setSearch("resurrection polypeptide cream");
        SearchResultsPage searchResultsPage = homePage.click_search_button();
        searchResultsPage.addToCart();

        WebDriverWait wait = new WebDriverWait(searchResultsPage.getDriver(), Duration.ofSeconds(10));
        wait.until(searchResultsPage.isTitleVisible());

        assertEquals(AssertionMessages.CORRECT_ITEM_NOT_ADDED, "resurrection polypeptide cream", searchResultsPage.getCartItem());
    }
    @Test
    public void CocokindInvalidAddToCart(){
        homePage.setSearch("travel-size resurrection polypeptide cream"); //this item is sold out
        SearchResultsPage searchResultsPage = homePage.click_search_button();
        searchResultsPage.addToCartSO();

        assertFalse(AssertionMessages.CORRECT_ITEM_NOT_ADDED, searchResultsPage.isCartPresent());
    }
    @Test
    public void CocokindPlusOneInCart(){
        homePage.setSearch("resurrection polypeptide cream");
        SearchResultsPage searchResultsPage = homePage.click_search_button();
        searchResultsPage.addToCart();

        WebDriverWait wait = new WebDriverWait(searchResultsPage.getDriver(), Duration.ofSeconds(10));
        wait.until(searchResultsPage.isTitleVisible());


        //assertEquals(AssertionMessages.CORRECT_ITEM_NOT_ADDED, "resurrection polypeptide cream", searchResultsPage.getCartItem());
    }
}
