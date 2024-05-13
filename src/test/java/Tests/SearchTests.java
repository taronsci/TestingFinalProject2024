package Tests;

import org.junit.Test;
import pages.SearchResultsPage;
import static org.junit.Assert.*;
import constants.AssertionMessages;

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
    public void CocokindSortByPriceLtH() {
        driver.get(LINKLtH);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);

        double[] pr = searchResultsPage.getPrices();
        assertTrue(AssertionMessages.PRICESNOTSORTED, SearchResultsPage.isSorted(pr));
    }

}
