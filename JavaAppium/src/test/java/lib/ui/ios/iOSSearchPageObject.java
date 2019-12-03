package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class iOSSearchPageObject extends SearchPageObject {
    static {
            SEARCH_INIT_ELEMENT = "id:Search Wikipedia";
            SEARCH_INPUT = "xpath://XCUIElementTypeNavigationBar[@name='Wikipedia, scroll to top of Explore']";
            SEARCH_CANCEL_BUTTON = "id:Close";
            SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{SUBSTRING}')]";
            SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeLink";
            SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='No results found']";
    }
    public iOSSearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

}
