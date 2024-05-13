package Tests;

import constants.AssertionMessages;
import org.junit.Test;
import pages.SearchResultsPage;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
}
