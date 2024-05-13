package Tests;

import constants.AssertionMessages;
import org.junit.Test;
import pages.CheckoutPage;
import pages.SearchResultsPage;
import static org.junit.Assert.assertTrue;

public class CheckoutTests extends BaseTest{
    @Test
    public void CocokingCheckout(){
        homePage.setSearch("resurrection polypeptide cream");
        SearchResultsPage searchResultsPage = homePage.click_search_button();
        searchResultsPage.addToCart();

        CheckoutPage checkoutPage = searchResultsPage.click_checkout_button();
        checkoutPage.waits();

        assertTrue(AssertionMessages.CHECK_TEXT_INCORRECT, checkoutPage.getStatus());
    }
    @Test
    public void InvalidPayNow(){
        homePage.setSearch("resurrection polypeptide cream");
        SearchResultsPage searchResultsPage = homePage.click_search_button();
        searchResultsPage.addToCart();

        CheckoutPage checkoutPage = searchResultsPage.click_checkout_button();
        checkoutPage.waits();

        checkoutPage.setFirstname("Taron");
        checkoutPage.setLastname("Schisas");
        checkoutPage.setAddress("123 Blue Road");
        checkoutPage.click_PayNow_button();

        assertTrue(AssertionMessages.EMAIL_NOT_ADDED, checkoutPage.getEmailWarning());
    }
}
