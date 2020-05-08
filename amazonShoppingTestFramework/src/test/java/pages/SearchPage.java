package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchPage extends PageBase {

    public SearchPage(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\" Results\")")
    protected WebElement searchResults;

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"691\")")
    protected WebElement price;

    @AndroidFindBy(xpath = "//*[@resource-id='title_feature_div']")
    WebElement titleParent;

    @AndroidFindBy(id = "rs_search_src_text")
    protected WebElement searchBar;


    public boolean areSearchResultsVisible() {
        return waitForElementToBeVisible(searchResults);
    }

    public int getNumberOfResults() {
        String searchResultsText = searchResults.getText();
        return Integer.valueOf(searchResultsText.substring(0, searchResultsText.indexOf(" ")));
    }

    public boolean isSearchBarDisplayed() {
        return searchBar.isDisplayed();
    }

    public void waitFor() {
        if (!isSearchBarDisplayed()) {
            waitForElementToBeVisible(searchBar);
        }
    }

    public void clickSearchBar() {
        clickElementAfterWait(searchBar);
    }

    public void searchByQuery(String query) {
        clickSearchBar();
        typeText(searchBar, query);
        hitEnter();
    }

    public List<WebElement> resultsList(String searchString) {
        return driver.findElementsByAndroidUIAutomator("new UiSelector().textContains(\"" + searchString + "\")");
    }

    public void scrollToProductAndClick(String searchString) {
        List<WebElement> results = resultsList(searchString);
        clickElementAfterWait(results.get(results.size() - 2));
    }

    public String getResultTitle(String searchString) {
        List<WebElement> results = resultsList(searchString);
        String title = results.get(results.size() - 2).getText();
        clickElementAfterWait(results.get(results.size() - 2));
        return title;
    }

    public boolean verifyText(String expectedTitle) {
        WebElement expected = driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"" + expectedTitle.trim() + "\")");
        return waitForElementToBeVisible(expected);
    }

}
