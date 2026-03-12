package com.company.project.tests;

import com.company.project.drivers.DriverFactory;
import com.company.project.drivers.DriverManager;
import com.company.project.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeMethod
    public void setUp() {
        WebDriver driver = DriverFactory.createDriver(ConfigReader.getBrowser());
        DriverManager.setDriver(driver);
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
