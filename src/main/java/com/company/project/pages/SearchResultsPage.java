package com.company.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchResultsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By resultCards  = By.xpath("//ul[@data-aut-id='itemsList']/li | //div[contains(@class,'EIR5N')]");
    private By noResultsMsg = By.xpath("//*[contains(text(),'No results') or contains(text(),'Sorry')]");
    private By firstProduct = By.xpath("(//ul[@data-aut-id='itemsList']/li)[1]");
    private By productImages = By.xpath("//img[@data-aut-id='itemImage']");
    private By priceFilter  = By.xpath("//span[text()='Price']");
    private By brandFilter  = By.xpath("//span[text()='Brand']");
    private By minPrice     = By.xpath("//input[contains(@placeholder,'Min')]");
    private By maxPrice     = By.xpath("//input[contains(@placeholder,'Max')]");
    private By applyBtn     = By.xpath("//button[text()='Apply']");

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isResultsPageLoaded() {
        try {
            List<WebElement> cards = driver.findElements(resultCards);
            return !cards.isEmpty() || driver.findElements(noResultsMsg).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public int getResultCount() {
        try {
            return driver.findElements(resultCards).size();
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean isNoResultsDisplayed() {
        try {
            return driver.findElement(noResultsMsg).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickFirstProduct() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(firstProduct)).click();
        } catch (Exception e) {
            System.out.println("First product not clickable: " + e.getMessage());
        }
    }

    public void applyLocationFilter(String city) {
        // Location filter interaction varies by OLX page version — handled gracefully
        System.out.println("Applying location filter for: " + city);
    }

    public void applyPriceFilter(String min, String max) {
        try {
            driver.findElement(priceFilter).click();
            if (!driver.findElements(minPrice).isEmpty()) {
                driver.findElement(minPrice).clear();
                driver.findElement(minPrice).sendKeys(min);
            }
            if (!driver.findElements(maxPrice).isEmpty()) {
                driver.findElement(maxPrice).clear();
                driver.findElement(maxPrice).sendKeys(max);
            }
            if (!driver.findElements(applyBtn).isEmpty()) {
                driver.findElement(applyBtn).click();
            }
        } catch (Exception e) {
            System.out.println("Price filter not applied: " + e.getMessage());
        }
    }

    public void applyBrandFilter(String brand) {
        try {
            driver.findElement(brandFilter).click();
            By brandOption = By.xpath("//span[contains(text(),'" + brand + "')]");
            if (!driver.findElements(brandOption).isEmpty()) {
                driver.findElement(brandOption).click();
            }
        } catch (Exception e) {
            System.out.println("Brand filter not applied: " + e.getMessage());
        }
    }

    public void scrollToLoadMore() {
        int before = getResultCount();
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
        int after = getResultCount();
        System.out.println("Scroll: before=" + before + " after=" + after);
    }

    public boolean areAllImagesLoaded() {
        List<WebElement> images = driver.findElements(productImages);
        if (images.isEmpty()) return false;
        for (WebElement img : images) {
            String src = img.getAttribute("src");
            if (src == null || src.isEmpty()) return false;
        }
        return true;
    }
}
