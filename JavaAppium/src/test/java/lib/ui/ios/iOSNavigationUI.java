package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUi;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSNavigationUI extends NavigationUi
{
    static {
        MY_LISTS_LINK = "id:Saved";
        EXPLORE_LINK = "xpath://android.widget.FrameLayout[@content-desc='Explore']";
    }

    public iOSNavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }
}
