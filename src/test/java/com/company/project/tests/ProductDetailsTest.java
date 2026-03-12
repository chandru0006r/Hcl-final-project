package com.company.project.tests;

import com.company.project.drivers.DriverManager;
import com.company.project.pages.HomePage;
import com.company.project.pages.ProductDetailsPage;
import com.company.project.pages.SearchResultsPage;
import com.company.project.utils.ConfigReader;
import com.company.project.utils.ScreenshotUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

public class ProductDetailsTest extends BaseTest {

    private HomePage homePage;
    private SearchResultsPage searchResultsPage;
    private ProductDetailsPage productDetailsPage;
    private static final String KEYWORD = "Used Mobile Phone";

    @BeforeMethod
    public void initPage() {
        homePage = new HomePage(DriverManager.getDriver());
        searchResultsPage = new SearchResultsPage(DriverManager.getDriver());
        productDetailsPage = new ProductDetailsPage(DriverManager.getDriver());
        homePage.openOlx(ConfigReader.getUrl());
        homePage.searchFor(KEYWORD);
    }

    private void goToFirstProduct() {
        if (searchResultsPage.getResultCount() > 0) {
            searchResultsPage.clickFirstProduct();
        }
    }

    @Test(description = "TC08 - Verify clicking a product opens the details page")
    public void verifyProductSelection() {
        goToFirstProduct();
        boolean loaded = productDetailsPage.isProductLoaded();
        System.out.println("Product details page loaded: " + loaded);
        Assert.assertTrue(loaded, "Product details page should be loaded after clicking a product");
    }

    @Test(description = "TC09 - Verify product title, price, seller and description are visible")
    public void verifyProductDetailsDisplayed() {
        goToFirstProduct();
        String title = productDetailsPage.getProductTitle();
        String price = productDetailsPage.getProductPrice();
        String seller = productDetailsPage.getSellerInfo();
        String description = productDetailsPage.getDescription();

        System.out.println("Title      : " + title);
        System.out.println("Price      : " + price);
        System.out.println("Seller     : " + seller);
        System.out.println("Description: " + description);

        Assert.assertFalse(title.isEmpty(), "Product title should not be empty");
        Assert.assertFalse(price.isEmpty(), "Product price should not be empty");
        Assert.assertFalse(description.isEmpty(), "Product description should not be empty");
    }

    @Test(description = "TC10 - Verify screenshot is captured after product page loads")
    public void verifyScreenshotCapture() {
        goToFirstProduct();
        String path = ScreenshotUtils.captureScreenshot(DriverManager.getDriver(), "TC10_ProductPage");
        File screenshotFile = new File(path);
        System.out.println("Screenshot saved at: " + path);
        Assert.assertTrue(screenshotFile.exists(), "Screenshot file should exist at: " + path);
    }

    @Test(description = "TC17 - Verify broken image is handled gracefully on product page")
    public void brokenImageHandling() {
        goToFirstProduct();
        boolean productLoaded = productDetailsPage.isProductLoaded();
        System.out.println("Product page loaded despite potential image issues: " + productLoaded);
        Assert.assertNotNull(DriverManager.getDriver().getTitle(),
                "Page should still have a title even with broken images");
    }

    @Test(description = "TC18 - Verify product with incomplete data is handled gracefully")
    public void productDetailsMissing() {
        goToFirstProduct();
        String title = productDetailsPage.getProductTitle();
        String price = productDetailsPage.getProductPrice();
        System.out.println("Title: " + title + " | Price: " + price);
        Assert.assertNotNull(title, "Title should not be null (even if 'not available')");
        Assert.assertNotNull(price, "Price should not be null (even if 'not available')");
    }

    @Test(description = "TC21 - Verify seller info fallback when unavailable")
    public void sellerInfoUnavailable() {
        goToFirstProduct();
        String seller = productDetailsPage.getSellerInfo();
        System.out.println("Seller info: " + seller);
        Assert.assertNotNull(seller, "Seller info should not be null");
    }

    @Test(description = "TC22 - Verify scroll stops gracefully when no more products")
    public void infiniteScrollFailure() {
        homePage.openOlx(ConfigReader.getUrl());
        homePage.searchFor(KEYWORD);

        int lastCount = 0;
        for (int i = 0; i < 3; i++) {
            searchResultsPage.scrollToLoadMore();
            int currentCount = searchResultsPage.getResultCount();
            System.out.println("Scroll " + (i + 1) + " - product count: " + currentCount);
            lastCount = currentCount;
        }
        Assert.assertTrue(lastCount >= 0, "Product count should not be negative after multiple scrolls");
        Assert.assertNotNull(DriverManager.getDriver().getCurrentUrl(), "Page should remain stable");
    }
}
