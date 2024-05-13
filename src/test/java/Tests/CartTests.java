package Tests;

import constants.AssertionMessages;
import org.junit.Test;
import pages.CheckoutPage;
import pages.SearchResultsPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class CartTests extends BaseTest{

    @Test
    public void CocokindValidAddToCart(){
        homePage.setSearch("resurrection polypeptide cream");
        SearchResultsPage searchResultsPage = homePage.click_search_button();
        searchResultsPage.addToCart();

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
    public void CocokindPlusOneInCart() {
        homePage.setSearch("resurrection polypeptide cream");
        SearchResultsPage searchResultsPage = homePage.click_search_button();
        searchResultsPage.addToCart();
        searchResultsPage.click_plus();

        assertEquals(AssertionMessages.COUNTNOTCHANGEDCORRECTLY, "2", searchResultsPage.getAmount());
    }
    @Test
    public void CocokindMinusOneInCart() {
        homePage.setSearch("resurrection polypeptide cream");
        SearchResultsPage searchResultsPage = homePage.click_search_button();
        searchResultsPage.addToCart();
        searchResultsPage.click_minus();

        assertEquals(AssertionMessages.COUNTNOTCHANGEDCORRECTLY, "0", searchResultsPage.getCartCount());
    }
    @Test
    public void CocokindTooManyItems() {
        homePage.setSearch("resurrection polypeptide cream");
        SearchResultsPage searchResultsPage = homePage.click_search_button();
        searchResultsPage.addToCart();
        searchResultsPage.setItemCount("50");
        searchResultsPage.click_checkout_button();
        searchResultsPage.waits();

        assertTrue(AssertionMessages.INVALIDPURCHASECOUNT, searchResultsPage.isDialogDisplayed());
    }
    @Test
    public void CocokindItemsBoundary() {
        homePage.setSearch("resurrection polypeptide cream");
        SearchResultsPage searchResultsPage = homePage.click_search_button();
        searchResultsPage.addToCart();
        searchResultsPage.setItemCount("4");
        searchResultsPage.click_checkout_button();
        CheckoutPage checkoutPage = searchResultsPage.click_checkout_button();
        checkoutPage.waits();

        assertTrue(AssertionMessages.CHECK_TEXT_INCORRECT, checkoutPage.getStatus());
    }
}
