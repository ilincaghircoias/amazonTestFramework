package tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.PageBase;
import pages.SearchPage;
import pages.SignInSignOutPage;
import testBase.BaseTestClass;

public class PurchaseTest extends BaseTestClass {

    private static final String RESULT_TEXT = "inch";

    SignInSignOutPage signInSignOut = new SignInSignOutPage(driver);
    SearchPage globalSearch = new SearchPage(driver);
    PageBase pageBase = new PageBase(driver);

    @Test
    @Parameters({"query", "email", "password"})
    public void purchaseTest(String query, String email, String password) throws InterruptedException {

        // Step: Sign in with existing account
        signInSignOut.waitFor();
        signInSignOut.clickSkipSignIn();

        // Step: wait for search page to load
        globalSearch.waitFor();

        // Step: search by given query (65-ich TV)
        // query value read from .xml file and passed through @Parameter
        globalSearch.searchByQuery(query);

        // Verify: Search results are visible after search by keyword
        Assert.assertTrue(globalSearch.getNumberOfResults() > 0, "No search results are displayed after search by keyword" + query);

        // Verify: Search results are visible
        Assert.assertTrue(globalSearch.areSearchResultsVisible(), "Search results not visible after search by keyword" + query);

        // Step: Scroll to one of the results and click
        globalSearch.scrollToProductAndClick(RESULT_TEXT);

        // Step: Get title of product after click
        String resultsText =  globalSearch.getResultTitle(RESULT_TEXT);

        // Step: Validate title
        Assert.assertTrue(globalSearch.verifyText(resultsText), "Title not validated");

        // Sign out
        pageBase.goBackFromAndroidXTimes(1);
        signInSignOut.signOut();
    }
}
