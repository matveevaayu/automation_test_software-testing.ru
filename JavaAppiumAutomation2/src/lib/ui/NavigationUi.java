package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUi extends MainPageObject{

    private static final String
        MY_LISTS_LINK = "//android.widget.FrameLayout[@content-desc='My lists']",
        EXPLORE_LINK = "//android.widget.FrameLayout[@content-desc='Explore']";

    public NavigationUi(AppiumDriver driver)
    {
        super(driver);
    }

    public void clickMyLists()
    {
        this.waitForElementAndClick(
                By.xpath(MY_LISTS_LINK),
                "Cannot find navigation button to My list",
                5
        );
    }

    public void clickExplore()
    {
        this.waitForElementAndClick(
                By.xpath(EXPLORE_LINK),
                "Cannot find navigation button to Explore",
                5
        );
    }
}
