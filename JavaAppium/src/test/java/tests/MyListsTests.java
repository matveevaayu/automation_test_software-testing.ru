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
        login = "MatveevaAlina",
        password = "alina12345";

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

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.addFirstArticleToMyList(name_of_folder);
        }
        if(Platform.getInstance().isIOS()) {
        ArticlePageObject.addArticlesToMySaved();
        }
        if (Platform.getInstance().isMw()){
            NavigationUi NavigationUi = NavigationUIFactory.get(driver);
            NavigationUi.clickAddTitleInSaved();
        }

        if(Platform.getInstance().isIOS()) {
            ArticlePageObject.closeSyncSavedArticleOverlay();
        }
        if (Platform.getInstance().isMw()){
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Auth.clickAuthButton();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Auth.enterLoginData(login, password);


            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Auth.submitForm();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ArticlePageObject.waitForTitleElement();
            assertEquals("We are not on the same page after login",
                    article_title,
                    ArticlePageObject.getArticleTitle()
            );

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ArticlePageObject.addArticlesToMySaved();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ArticlePageObject.closeArticle();

        NavigationUi NavigationUi = NavigationUIFactory.get(driver);
        NavigationUi.openNavigation();
        NavigationUi.clickMyLists();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(Platform.getInstance().isIOS()) {
            ArticlePageObject.addArticlesToMySaved();
        }
        if (Platform.getInstance().isMw()){
            NavigationUi NavigationUi = NavigationUIFactory.get(driver);
            NavigationUi.clickAddTitleInSaved();
        }
        if(Platform.getInstance().isIOS()) {
            ArticlePageObject.closeSyncSavedArticleOverlay();
        }

        if (Platform.getInstance().isMw()){
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Auth.clickAuthButton();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Auth.enterLoginData(login, password);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Auth.submitForm();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ArticlePageObject.waitForTitleElement();
            assertEquals("We are not on the same page after login",
                    article_title,
                    ArticlePageObject.getArticleTitle()
            );

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ArticlePageObject.addArticlesToMySaved();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        ArticlePageObject.closeArticle();

        if(Platform.getInstance().isAndroid()) {
            NavigationUi NavigationUi = NavigationUIFactory.get(driver);
            NavigationUi.openNavigation();
            NavigationUi.clickMyLists();

            MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);


            MyListsPageObject.openFolderByName(name_of_folder);
            MyListsPageObject.waitForArticleToAppearByTitle(title_first_page_for_save);
            driver.navigate().back();
            NavigationUi.clickExplore();
        }


        SearchPageObject.initSearchInput();
        if(Platform.getInstance().isAndroid()){
        SearchPageObject.typeSearchLine("JUnit");
        }
        if (Platform.getInstance().isMw()) {
            SearchPageObject.typeSearchLine("JUnit");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        String title_second_page_for_save = "JUnit";
        SearchPageObject.clickByArticleWithSubstring("nit testing library for Java");

        ArticlePageObject.waitForTitleElement();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(Platform.getInstance().isAndroid()) {
            ArticlePageObject.addSubsequentArticleToMyList(name_of_folder);
        }
        if(Platform.getInstance().isIOS()) {
            ArticlePageObject.addArticlesToMySaved();
        }
        if (Platform.getInstance().isMw()){
            ArticlePageObject.addArticlesToMySaved();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        ArticlePageObject.closeArticle();

        NavigationUi NavigationUi = NavigationUIFactory.get(driver);
        NavigationUi.openNavigation();
        NavigationUi.clickMyLists();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if(Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        }

        MyListsPageObject.swipeByArticleToDelete(title_second_page_for_save);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MyListsPageObject.clickByArticleWithSubstring(title_first_page_for_save);

        String title = ArticlePageObject.getArticleTitle();
        assertEquals(
                "Article title have been changed after delete title 'Java (programming language)'",
                title,
                title_first_page_for_save
        );
    }
}
