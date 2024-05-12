package Tests;

import org.junit.Test;
import pages.CheckoutPage;
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
        assertEquals(AssertionMessages.CHECK_TEXT_INCORRECT, "search results", searchResultsPage.getStatusText());
    }
    @Test
    public void CocokindInvalidSearch(){
        homePage.setSearch("button");
        SearchResultsPage searchResultsPage = homePage.click_search_button();
        assertEquals(AssertionMessages.SEARCH_RESULTS_NOT_FOUND,"No results found for “button”. Check the spelling or use a different word or phrase.", searchResultsPage.getErrorStatusText());
    }
    @Test
    public void CocokindBlankSearch(){
        homePage.setSearch("");
        homePage.click_cancelSearch_button();
        assertTrue(AssertionMessages.BLANK_SEARCH, homePage.isCancelSearchEnabled());
    }
    @Test
    public void CocokindAddToCart(){
        homePage.setSearch("resurrection polypeptide cream");
        SearchResultsPage searchResultsPage = homePage.click_search_button();
        searchResultsPage.addToCart();

        WebDriverWait wait = new WebDriverWait(searchResultsPage.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultsPage.productTitle));

        assertEquals(AssertionMessages.CORRECT_ITEM_NOT_ADDED, "resurrection polypeptide cream", searchResultsPage.getCartItem());

    }
    @Test
    public void CocokingCheckout(){
        homePage.setSearch("resurrection polypeptide cream");
        SearchResultsPage searchResultsPage = homePage.click_search_button();
        searchResultsPage.addToCart();

        WebDriverWait wait = new WebDriverWait(searchResultsPage.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultsPage.productTitle));

        CheckoutPage checkoutPage = searchResultsPage.click_checkout_button();

        wait = new WebDriverWait(checkoutPage.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutPage.statusCheck));

        assertTrue(AssertionMessages.CHECK_TEXT_INCORRECT, checkoutPage.getStatus());
    }
    @Test
    public void InvalidPayNow(){
        homePage.setSearch("resurrection polypeptide cream");
        SearchResultsPage searchResultsPage = homePage.click_search_button();
        searchResultsPage.addToCart();

        WebDriverWait wait = new WebDriverWait(searchResultsPage.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultsPage.productTitle));

        CheckoutPage checkoutPage = searchResultsPage.click_checkout_button();

        wait = new WebDriverWait(checkoutPage.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutPage.statusCheck));

        checkoutPage.setFirstname("Taron");
        checkoutPage.setLastname("Schisas");
        checkoutPage.setAddress("123 Blue Road");
        checkoutPage.click_PayNow_button();

        assertTrue(AssertionMessages.EMAIL_NOT_ADDED, checkoutPage.getEmailWarning());
    }
}
