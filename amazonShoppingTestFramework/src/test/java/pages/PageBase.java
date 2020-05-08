package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageBase {

    protected AndroidDriver driver;

    public PageBase(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    protected void typeText(WebElement inputField, String text) {
        clickElementAfterWait(inputField);
        inputField.clear();
        driver.getKeyboard().sendKeys(text);
    }

    protected void hitEnter() {
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    public void goBackFromAndroidXTimes(int times) {
        for(int i=0; i<times; i++){
            driver.pressKey(new KeyEvent(AndroidKey.BACK));
        }
    }

    protected void swipeScreenUp(int noOfSwipes) {
        for (int i = 0; i < noOfSwipes; i++) {
            swipeVertical(driver, 0.75, 0.5, 0.5, 2000);
        }
    }

    protected void swipeVertical(AppiumDriver driver, double start, double end, double width, int duration) {
        Dimension size = driver.manage().window().getSize();
        int central = (int) (size.width * width);
        int startPoint = (int) (size.height * start);
        int endPoint = (int) (size.height * end);

        new TouchAction(driver).press(PointOption.point(central, startPoint))
                .waitAction()
                .moveTo(PointOption.point(central, endPoint))
                .release().perform();
    }

    protected boolean waitForElementToBeVisible(WebElement element) {
        return waitForElementToBeVisible(element, 15);
    }

    protected boolean waitForElementToBeVisibleForXSeconds(WebElement element, int seconds) {
        return waitForElementToBeVisible(element, seconds);
    }

    protected boolean waitForElementToBeVisible(WebElement element, int timeout) {
        if (element == null) {
            return false;
        }
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
        return true;
    }

    protected boolean isElementVisible(WebElement element) {
        if (element == null) return false;
        return element.isDisplayed();
    }

    protected void clickElementAfterWait(WebElement element) {
        waitForElementToBeVisible(element);
        element.click();
    }

    public void switchToLandscape() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    public void switchToPortrait() {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    public void swipeDownXNumberOfSteps(int steps) {
        for (int i = 0; i < steps; i++) {
            swipeVertical(driver, 0.25, 0.5, 0.5, 1000);
        }
    }
}
