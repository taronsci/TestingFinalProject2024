package Tests;

import constants.AssertionMessages;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CheckoutPage;
import pages.SearchResultsPage;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class CheckoutTests extends BaseTest{
    @Test
    public void CocokingCheckout(){
        homePage.setSearch("resurrection polypeptide cream");
        SearchResultsPage searchResultsPage = homePage.click_search_button();
        searchResultsPage.addToCart();

        WebDriverWait wait = new WebDriverWait(searchResultsPage.getDriver(), Duration.ofSeconds(10));
        wait.until(searchResultsPage.isTitleVisible());

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
        wait.until(searchResultsPage.isTitleVisible());

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
