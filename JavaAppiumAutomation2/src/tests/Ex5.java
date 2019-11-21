package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUi;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class Ex5 extends CoreTestCase {

    @Test
    public void testSaveTwoArticlesToMyList()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        String title_first_page_for_save = "Java (programming language)";
        SearchPageObject.clickByArticleWithSubstring(title_first_page_for_save);

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        ArticlePageObject.waitForTitleElement();
        String name_of_folder = "Learning programming";
        ArticlePageObject.addFirstArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUi NavigationUi = new NavigationUi(driver);

        NavigationUi.clickMyLists();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);

        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.waitForArticleToAppearByTitle(title_first_page_for_save);
        driver.navigate().back();

        NavigationUi.clickExplore();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        String title_second_page_for_save = "JavaScript";
        SearchPageObject.clickByArticleWithSubstring(title_second_page_for_save);

        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.addSubsequentArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUi.clickMyLists();
        MyListsPageObject.openFolderByName(name_of_folder);

        MyListsPageObject.swipeByArticleToDelete(title_second_page_for_save);

        MyListsPageObject.clickByArticleWithSubstring(title_first_page_for_save);
        String title = ArticlePageObject.getArticleTitle();
        assertEquals(
                "Article title have been changed after delete title 'Java (programming language)'",
                title,
                title_first_page_for_save
        );
    }
}
