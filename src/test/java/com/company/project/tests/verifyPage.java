package test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class verifyPage {

    @Test
    public void LoginAndLogoutTest() throws InterruptedException {

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_leak_detection", false);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        String baseUrl = "http://olx.in/panki_g5341514/mobile-phones_c1453";
        driver.get(baseUrl);
        driver.manage().window().maximize();

        WebElement clickableProduct = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector(
                                "div ul[class='_266Ly _10aCo'] li:nth-child(1) a:nth-child(1) div:nth-child(1)")));

        clickableProduct.click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));

        System.out.println("Product page title: " + driver.getTitle());

        driver.quit();
    }
}
