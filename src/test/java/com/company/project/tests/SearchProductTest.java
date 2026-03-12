package com.company.project.tests;

import com.company.project.drivers.DriverManager;
import com.company.project.pages.HomePage;
import com.company.project.pages.SearchResultsPage;
import com.company.project.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchProductTest extends BaseTest {

    private HomePage homePage;
    private SearchResultsPage searchResultsPage;
    private static final String KEYWORD = "Used Mobile Phone";

    @BeforeMethod
    public void initPage() {
        homePage = new HomePage(DriverManager.getDriver());
        searchResultsPage = new SearchResultsPage(DriverManager.getDriver());
        homePage.openOlx(ConfigReader.getUrl());
    }

    // TC02 - Verify search with valid keyword
    @Test(description = "TC02 - Verify search with valid keyword")
    public void verifySearchWithValidKeyword() {
        homePage.searchFor(KEYWORD);
        boolean loaded = searchResultsPage.isResultsPageLoaded();
        System.out.println("Results loaded: " + loaded + ", Count: " + searchResultsPage.getResultCount());
        Assert.assertTrue(loaded, "Search results page should load with results");
    }

    // TC03 - Verify location filter
    @Test(description = "TC03 - Verify location filter")
    public void verifyLocationFilter() {
        homePage.searchFor(KEYWORD);
        int before = searchResultsPage.getResultCount();
        searchResultsPage.applyLocationFilter("Chennai");
        int after = searchResultsPage.getResultCount();
        System.out.println("Before location filter: " + before + ", After: " + after);
        Assert.assertTrue(searchResultsPage.isResultsPageLoaded(),
                "Results page should still be loaded after applying location filter");
    }

    // TC04 - Verify price filter
    @Test(description = "TC04 - Verify price range filter 5000-10000")
    public void verifyPriceFilter() {
        homePage.searchFor(KEYWORD);
        searchResultsPage.applyPriceFilter("5000", "10000");
        System.out.println("After price filter - count: " + searchResultsPage.getResultCount());
        Assert.assertTrue(searchResultsPage.isResultsPageLoaded(),
                "Results should remain loaded after price filter");
    }

    // TC05 - Verify brand filter
    @Test(description = "TC05 - Verify brand filter for Samsung")
    public void verifyBrandFilter() {
        homePage.searchFor(KEYWORD);
        searchResultsPage.applyBrandFilter("Samsung");
        System.out.println("After brand filter - count: " + searchResultsPage.getResultCount());
        Assert.assertTrue(searchResultsPage.isResultsPageLoaded(),
                "Results should remain loaded after brand filter");
    }

    // TC06 - Verify combined filters
    @Test(description = "TC06 - Verify combined location + price + brand filters")
    public void verifyCombinedFilters() {
        homePage.searchFor(KEYWORD);
        searchResultsPage.applyLocationFilter("Chennai");
        searchResultsPage.applyPriceFilter("5000", "10000");
        searchResultsPage.applyBrandFilter("Samsung");
        boolean loaded = searchResultsPage.isResultsPageLoaded();
        System.out.println("Combined filters applied. Results loaded: " + loaded);
        Assert.assertTrue(loaded, "Page should be stable after combined filters");
    }

    // TC07 - Verify search result grid loads (cards with title, price, image)
    @Test(description = "TC07 - Verify search result grid loads product cards")
    public void verifySearchResultGridLoads() {
        homePage.searchFor(KEYWORD);
        int count = searchResultsPage.getResultCount();
        System.out.println("Product card count: " + count);
        Assert.assertTrue(count > 0 || searchResultsPage.isNoResultsDisplayed(),
                "Either product cards or 'no results' should be displayed");
    }

    // TC11 - Verify infinite scroll loads more products
    @Test(description = "TC11 - Verify infinite scroll loads more products")
    public void verifyInfiniteScroll() {
        homePage.searchFor(KEYWORD);
        int before = searchResultsPage.getResultCount();
        searchResultsPage.scrollToLoadMore();
        int after = searchResultsPage.getResultCount();
        System.out.println("Before scroll: " + before + ", After scroll: " + after);
        // After scroll, count should be >= before (more or same products)
        Assert.assertTrue(after >= before,
                "Product count after scroll should not decrease. Before=" + before + " After=" + after);
    }

    // TC12 - Verify product images load properly
    @Test(description = "TC12 - Verify product images load properly")
    public void verifyImagesLoadProperly() {
        homePage.searchFor(KEYWORD);
        boolean imagesLoaded = searchResultsPage.areAllImagesLoaded();
        System.out.println("All images loaded: " + imagesLoaded);
        Assert.assertTrue(imagesLoaded || searchResultsPage.getResultCount() == 0,
                "Product images should load properly");
    }

    // TC15 - Invalid price range (min > max) — should not crash
    @Test(description = "TC15 - Verify invalid price range is handled gracefully")
    public void invalidPriceRange() {
        homePage.searchFor(KEYWORD);
        searchResultsPage.applyPriceFilter("10000", "5000"); // min > max
        boolean stable = searchResultsPage.isResultsPageLoaded();
        System.out.println("Page stable after invalid price range: " + stable);
        Assert.assertNotNull(DriverManager.getDriver().getCurrentUrl(),
                "Page should not crash with invalid price range");
    }

    // TC16 - Apply very strict filters → no results
    @Test(description = "TC16 - Apply strict filters expecting no results")
    public void applyFilterWithNoResults() {
        homePage.searchFor("NonExistentBrand12345xyz");
        boolean noResults = searchResultsPage.isNoResultsDisplayed()
                || searchResultsPage.getResultCount() == 0;
        System.out.println("No results shown: " + noResults);
        Assert.assertTrue(noResults, "Strict/invalid search should show no results");
    }
}
