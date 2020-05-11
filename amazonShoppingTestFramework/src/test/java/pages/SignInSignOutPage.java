package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class SignInSignOutPage extends PageBase {

    public SignInSignOutPage(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(className = "UIAKeyboard")
    protected WebElement keyboard;

    @AndroidFindBy(xpath = "//*[@resource-id='action_bar_burger_icon']")
    protected WebElement actionBar;

    @AndroidFindBy(xpath = "//*[@resource-id='drawer_item_title']", uiAutomator = "new UiSelector().textContains(\"Settings\")")
    protected WebElement settingsButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Sign Out\")")
    protected WebElement signOutButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Sign-In\")")
    protected WebElement signInButton;

    @AndroidFindBy(xpath = "//*[@resource-id='ap_email_login']")
    protected WebElement emailAddressField;

    @AndroidFindBy(xpath = "//*[@resource-id='continue']")
    protected WebElement continueButton;

    @AndroidFindBy(xpath = "//*[@resource-id='ap_password']")
    protected WebElement passwordField;

    @AndroidFindBy(xpath = "//*[@resource-id='signInSubmit']")
    protected WebElement signInSubmit;

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Not now\")")
    protected WebElement notNow;
    
    @AndroidFindBy(id = "skip_sign_in_button")
    protected WebElement skipSignInButton;
    
    public void clickSkipSignIn() {
        clickElementAfterWait(skipSignInButton);
    }

    public void enterEmailAddress(String email) {
        clickElementAfterWait(emailAddressField);
        typeEmail(email);
    }

    public void enterPassword(String pass) {
        clickElementAfterWait(passwordField);
        typePassword(pass);
    }

    public boolean isSigninButtonDisplayed() {
        return isElementVisible(signInButton);
    }

    public void waitFor() {
        if (!isSigninButtonDisplayed()) {
            waitForElementToBeVisibleForXSeconds(signInButton, 30);
        }
    }

    public void typeEmail(String name) {
        typeText(emailAddressField, name);
    }

    public void clickContinueButton() {
        clickElementAfterWait(continueButton);
    }

    public void typePassword(String password) {
        typeText(passwordField, password);
    }

    public void clickSignInSubmit() {
        clickElementAfterWait(signInSubmit);
    }

    public void hideKeyboardIfVisible() {
        if (keyboard != null) {
            driver.pressKey(new KeyEvent(AndroidKey.ESCAPE));
        }
    }

    public void clickActionBar() {
        clickElementAfterWait(actionBar);
    }


    public void clickSettingsButton() {
        clickElementAfterWait(settingsButton);
    }


    public void clickSignOutButtonSubmit() {
        clickElementAfterWait(signOutButton);
    }

    public void signInFlow(String email, String password) {
        clickElementAfterWait(signInButton);
        enterEmailAddress(email);
        hideKeyboardIfVisible();
        clickContinueButton();
        enterPassword(password);
        hideKeyboardIfVisible();
        clickElementAfterWait(signInSubmit);
        if (waitForElementToBeVisible(notNow)) {
            clickElementAfterWait(notNow);
        }
    }

    public void signOut() {
        clickActionBar();
        clickSettingsButton();
        clickSignOutButtonSubmit();
    }
}
