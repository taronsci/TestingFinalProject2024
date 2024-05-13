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
        homePage.setSearch("$.b1u*t`t~o'n+");
        SearchResultsPage searchResultsPage = homePage.click_search_button();
        assertEquals(AssertionMessages.SEARCH_RESULTS_NOT_FOUND,"No results found for “$.b1u*t`t~o'n+”. Check the spelling or use a different word or phrase.", searchResultsPage.getErrorStatusText());
    }
    @Test
    public void CocokindBlankSearch(){
        homePage.setSearch("");
        homePage.click_cancelSearch_button();
        assertTrue(AssertionMessages.BLANK_SEARCH, homePage.isCancelSearchEnabled());
    }
    @Test
    public void CocokindSortByPriceLtH(){
        homePage.setSearch("cream");
        SearchResultsPage searchResultsPage = homePage.click_search_button();
        searchResultsPage.switchSortToLtH();
        searchResultsPage.click_header();

        double[] pr = searchResultsPage.getPrices();
//        for(double i:pr) {
//            if(i != 0.0)
//                System.out.print(i + " ");
//        }

        //will fail until switchSortToLtH() method is fixed
        assertTrue(AssertionMessages.PRICESNOTSORTED, SearchResultsPage.isSorted(pr));
    }

}
