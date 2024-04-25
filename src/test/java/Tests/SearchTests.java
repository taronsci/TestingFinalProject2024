package Tests;

import org.junit.Test;
import pages.SearchResultsPage;
import static org.junit.Assert.*;

public class SearchTests extends BaseTest {

    @Test
    public void CocokindValidSearch(){
        homePage.close_Ad();
        homePage.setSearch("cream");
        SearchResultsPage searchResultsPage = homePage.click_search_button();
        assertEquals("Alert text is incorrect", "search results", searchResultsPage.getAlertText());
    }
}
