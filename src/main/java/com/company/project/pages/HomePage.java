package com.company.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By searchBar    = By.xpath("//input[@data-aut-id='searchBox']");
    private By searchButton = By.xpath("//button[@data-aut-id='btnSearch']");
    private By noResultsMsg = By.xpath("//*[contains(text(),'No results') or contains(text(),'no result')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void openOlx(String url) {
        driver.get(url);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getHomePageTitle() {
        return driver.getTitle();
    }

    public void searchFor(String keyword) {
        WebElement sb = wait.until(ExpectedConditions.elementToBeClickable(searchBar));
        sb.clear();
        sb.sendKeys(keyword);
        sb.sendKeys(Keys.ENTER);
    }

    public void clickSearchWithoutKeyword() {
        try {
            WebElement sb = driver.findElement(searchBar);
            sb.clear();
            driver.findElement(searchButton).click();
        } catch (Exception e) {
            System.out.println("Search button not found: " + e.getMessage());
        }
    }

    public boolean isSearchBarDisplayed() {
        try {
            return driver.findElement(searchBar).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isNoResultsMessageDisplayed() {
        try {
            return driver.findElement(noResultsMsg).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
