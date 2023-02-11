package com.yandex;

import org.junit.Test;
import src.main.OrderScooterPageObject;

public class OrderScooterTest extends BaseTest {
    @Test
    public void testFirstWayOrderScooter() {
        driver.get("https://qa-scooter.praktikum-services.ru/");

        OrderScooterPageObject orderScooterPageObject = new OrderScooterPageObject(driver);

        orderScooterPageObject.init(driver);

        orderScooterPageObject.checkOrderScooterFirstWay(
                "Иван",
                "Иванов",
                "Москва",
                823234234324L,
                "08.02.2023",
                "test comment"
        );
    }
}
