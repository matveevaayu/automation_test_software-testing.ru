package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class Ex3 extends CoreTestCase {

    @Test
    public void testSearchForMultipleArticles()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        int amount_of_search_results = SearchPageObject.getAmmountOfFoundArticles();
        assertTrue(
                "We found only one article!",
                amount_of_search_results > 1
        );
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForSearchResultToDisappear();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

}
