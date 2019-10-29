import com.sun.xml.internal.ws.wsdl.writer.document.StartWithExtensionsType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.plugin2.message.Message;

import javax.swing.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FirstTest {

    private AppiumDriver driver;
    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","6.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("addPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","/Users/matveevaayu/Documents/GitHub/automation_test_software-testing.ru/JavaAppiumAutomation2/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void saveTwoArticlesToMyList()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_title'][@text='Java (programming language)']"),
                "Cannot find 'Java' in search",
                5
        );
        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it' tip overlay",
                5
        );
        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of articles holder",
                5
        );
        String name_of_folder = "Learning programming";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press 'OK' button",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X link",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation button to My list",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find created folder",
                5
        );
        waitForElementPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find saved article",
                5
        );

        driver.navigate().back();

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='Explore']"),
                "Cannot find navigation button to Explorer",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_title'][@text='JavaScript']"),
                "Cannot find 'Java' in search",
                5
        );
        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/item_container']//*[@text='" + name_of_folder + "']"),
                "Cannot find folder " + name_of_folder,
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X link",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation button to My list",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find created folder",
                5
        );
        waitForElementPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find saved article",
                15
        );
        waitForElementPresent(
                By.xpath("//*[@text='JavaScript']"),
                "Cannot find saved article",
                5
        );
        swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find saved article"
        );
        waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot delete saved article",
                5
        );
        String title = waitForElementAndGetAttribute(
                By.xpath("//*[@text='JavaScript']"),
                "text",
                "Cannot find title of article",
                15
        );
        waitForElementAndClick(
                By.xpath("//*[@text='JavaScript']"),
                "Cannot find 'JavaScript' article after delete article 'Java (programming language)'",
                5
        );
        String title_title = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15
        );
        Assert.assertEquals(
                "Article title have been changed after delete title 'Java (programming language)'",
                title,
                title_title
        );
    }



    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");

        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String error_message){
        return  waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.click();
        return element;
    }
    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.clear();
        return element;
    }
    protected void swipeUp(int timeOfSwipe){
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);
        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    protected void swipeUpQuck()
    {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes)
    {
        driver.findElements(by);
        driver.findElements(by).size();
        int already_swipe = 0;
        while(driver.findElements(by).size() == 0)
        {
            if(already_swipe > max_swipes)
            {
                waitForElementPresent(by, "Cannot find element be swiping up. \n" + error_message, 0);
                return;
            }
            swipeUpQuck();
            ++already_swipe;
        }
    }
    protected void swipeElementToLeft(By by, String error_message)
    {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();

    }

    private int getAmmountOfElements(By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private void assertElementNotPresent(By by, String error_message)
    {
        int amount_of_elements = getAmmountOfElements(by);
        if (amount_of_elements > 0)
        {
            String default_message = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }
}

