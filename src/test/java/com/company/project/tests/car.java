package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class car {
        @Test
        public void f() {

                WebDriver driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.get("https://www.olx.in/");

                driver.findElement(
                                By.xpath("//*[@id=\"main_content\"]/div/section/div[2]/div[2]/div/div/div[1]/div/img"))
                                .click();

                driver.findElement(By.xpath(
                                "//*[@id=\"main_content\"]/div/div/section/div/div/div[5]/div[2]/div[1]/div[2]/ul/li[1]/a/div/div[2]/div[1]/span"))
                                .click();

                String actualText = driver
                                .findElement(By.xpath(
                                                "//*[@id=\"main_content\"]/div/div[2]/div/div[4]/div[1]/div[1]/div/div/h1"))
                                .getText();

                System.out.println("Actual Text : " + actualText);

        }
}