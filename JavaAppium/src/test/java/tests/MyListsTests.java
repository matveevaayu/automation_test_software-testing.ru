package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase
{
    private static final String name_of_folder = "Learning programming";
    private static final String
        login = "Matveevaayu",
        password = "me7eePei";

    @Test
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.addFirstArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
            ArticlePageObject.closeSyncSavedArticleOverlay();
        }
        if (Platform.getInstance().isMw()){
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();
            assertEquals("We are not on the same page after login",
                    article_title,
                    ArticlePageObject.getArticleTitle()
            );

            ArticlePageObject.addArticlesToMySaved();
        }
        ArticlePageObject.closeArticle();

        NavigationUi NavigationUi = NavigationUIFactory.get(driver);
        NavigationUi.openNavigation();
        NavigationUi.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if(Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        }
        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    public void testSaveTwoArticlesToMyList()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        String title_first_page_for_save = "Java (programming language)";
        SearchPageObject.clickByArticleWithSubstring(title_first_page_for_save);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        ArticlePageObject.waitForTitleElement();
        String name_of_folder = "Learning programming";
        ArticlePageObject.addFirstArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUi NavigationUi = NavigationUIFactory.get(driver);

        NavigationUi.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

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
