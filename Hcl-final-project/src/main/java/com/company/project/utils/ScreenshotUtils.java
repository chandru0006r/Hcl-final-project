package com.company.project.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    public static String captureScreenshot(WebDriver driver, String testName) {

        String screenshotDir = System.getProperty("user.dir") +
                "/src/test/resources/screenshots/";

        try {
            Files.createDirectories(Paths.get(screenshotDir));
        } catch (Exception e) {
            e.printStackTrace();
        }

        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String filePath = screenshotDir + testName + "_" + timestamp + ".png";

        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Files.write(Paths.get(filePath), screenshot);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return filePath;
    }

    public static String captureScreenshot(WebDriver driver) {
        return captureScreenshot(driver, "Screenshot");
    }
}