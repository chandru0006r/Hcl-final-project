package com.company.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductDetailsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By productTitle = By.xpath("//h1[@data-aut-id='itemTitle'] | //h1[contains(@class,'title')]");
    private By productPrice = By.xpath("//*[@data-aut-id='itemPrice'] | //*[contains(@class,'price')]");
    private By sellerInfo   = By.xpath("//*[@data-aut-id='sellerName'] | //*[contains(@class,'seller')]");
    private By description  = By.xpath("//*[@data-aut-id='itemDescriptionContent'] | //div[contains(@class,'description')]");

    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isProductLoaded() {
        try {
            return !driver.findElements(productTitle).isEmpty() || !driver.findElements(productPrice).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public String getProductTitle() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(productTitle)).getText();
        } catch (Exception e) {
            return "Title not available";
        }
    }

    public String getProductPrice() {
        try {
            return driver.findElement(productPrice).getText();
        } catch (Exception e) {
            return "Price not available";
        }
    }

    public String getSellerInfo() {
        try {
            return driver.findElement(sellerInfo).getText();
        } catch (Exception e) {
            return "Seller info not available";
        }
    }

    public String getDescription() {
        try {
            return driver.findElement(description).getText();
        } catch (Exception e) {
            return "Description not available";
        }
    }
}
