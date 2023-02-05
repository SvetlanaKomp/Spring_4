package com.yandex;

import com.yandex.utils.OrderScooterBlock;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class OrderScooterTest {
    @Test
    public void testFirstWayOrderScooterInChrome(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        WebDriver driver = new ChromeDriver(options);

        driver.get("https://qa-scooter.praktikum-services.ru/");

        OrderScooterBlock orderScooterBlock = new OrderScooterBlock(driver);

        orderScooterBlock.checkOrderScooterFirstWay(
                "Иван",
                "Иванов",
                "Москва",
                823234234324L,
                "08.02.2023",
                "test comment"
        );

        driver.quit();
    }

    @Test
    public void testFirstWayOrderScooterInFirefox(){
        WebDriverManager.firefoxdriver().setup();

        WebDriver driver = new FirefoxDriver();

        driver.get("https://qa-scooter.praktikum-services.ru/");

        OrderScooterBlock orderScooterBlock = new OrderScooterBlock(driver);

        orderScooterBlock.checkOrderScooterFirstWay(
                "Иван",
                "Иванов",
                "Москва",
                823234234324L,
                "08.02.2023",
                "test comment"
        );

        driver.quit();
    }

}
