package com.company.project.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BaseTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void openOlxTest() {
        // Open the URL
        driver.get("https://www.olx.in/");
        System.out.println("Opened OLX successfully. Page title is: " + driver.getTitle());
    }
    =
    @Test
    public void searchProduct()
    {
        driver.get("https://www.olx.in/");
        driver.findElement(By.id("searchBox")).sendKeys("Mobile" + Keys.ENTER);
    }
    @Test
    public void 

    @AfterMethod
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
