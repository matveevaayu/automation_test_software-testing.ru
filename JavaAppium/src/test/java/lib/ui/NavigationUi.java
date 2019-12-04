package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

import static lib.ui.ArticlePageObject.OPTIONS_ADD_TO_MY_LIST_BUTTON;

abstract public class NavigationUi extends MainPageObject{

    protected static String
        MY_LISTS_LINK,
        EXPLORE_LINK,
        OPEN_NAVIGATION;

    public NavigationUi(RemoteWebDriver driver)
    {
        super(driver);
    }

    public void openNavigation()
    {
        if (Platform.getInstance().isMw()){
            this.waitForElementAndClick(OPEN_NAVIGATION, "Cannot find and click open navigation button", 5);
        } else {
            System.out.println("Method openNavigation() do nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }
    public void clickMyLists()
    {
        if (Platform.getInstance().isMw()) {
            this.tryClickElementWithFewAttempts(
                    MY_LISTS_LINK,
                    "Cannot find navigation button to My list",
                    5
            );
        } else {
            this.waitForElementAndClick(
                    MY_LISTS_LINK,
                    "Cannot find navigation button to My list",
                    15
            );
        }
    }
    public void clickAddTitleInSaved() {
            this.tryClickElementWithFewAttempts(
                    OPTIONS_ADD_TO_MY_LIST_BUTTON,
                    "Cannot find navigation button to My list",
                    5
            );
    }

    public void clickExplore()
    {
        this.waitForElementAndClick(
                EXPLORE_LINK,
                "Cannot find navigation button to Explore",
                5
        );
    }
}
