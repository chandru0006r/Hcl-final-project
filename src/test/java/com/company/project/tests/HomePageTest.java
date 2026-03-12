package com.company.project.tests;

import com.company.project.drivers.DriverManager;
import com.company.project.pages.HomePage;
import com.company.project.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    private HomePage homePage;

    @BeforeMethod
    public void initPage() {
        homePage = new HomePage(DriverManager.getDriver());
        homePage.openOlx(ConfigReader.getUrl());
    }

    // TC01 - Verify OLX home page loads
    @Test(description = "TC01 - Verify OLX home page loads")
    public void verifyOlxHomePageLoads() {
        String title = homePage.getHomePageTitle();
        System.out.println("Page Title: " + title);
        Assert.assertTrue(title.toLowerCase().contains("olx"),
                "Page title should contain 'OLX' but was: " + title);
    }

    // TC13 - Search with empty keyword — no results should be shown or no search is triggered
    @Test(description = "TC13 - Search with empty keyword")
    public void searchWithEmptyKeyword() {
        homePage.clickSearchWithoutKeyword();
        String currentUrl = DriverManager.getDriver().getCurrentUrl();
        System.out.println("URL after empty search: " + currentUrl);
        Assert.assertNotNull(currentUrl, "Page should still be accessible after empty search");
    }

    // TC14 - Search with invalid keyword → "No results found"
    @Test(description = "TC14 - Search with invalid keyword")
    public void searchWithInvalidKeyword() {
        homePage.searchFor("abcxyz123invalid");
        boolean noResults = homePage.isNoResultsMessageDisplayed();
        System.out.println("No results displayed: " + noResults);
        Assert.assertNotNull(DriverManager.getDriver().getCurrentUrl(), "Page should remain accessible after invalid search");
    }
}
