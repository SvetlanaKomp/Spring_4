package com.yandex.utils;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class OrderScooterBlock {
    private final WebDriver driver;

    private By firstOrderButton =  By.xpath("//button[text()='Заказать']");
    private By secondOrderButton = By.className("Button_Button__ra12g");

    private By orderContent = By.className("Order_Content__bmtHS");
    private By clientName = By.xpath("//input[@placeholder='* Имя']");
    private By clientSecondName = By.xpath("//input[@placeholder='* Фамилия']");
    private By clientAddress = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private By clientMetroStation = By.xpath("//input[@placeholder='* Станция метро']");
    private By clientPhone = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By nextButton = By.xpath("//button[text()='Далее']");

    private By rentContent = By.className("Order_Content__bmtHS");

    private By clientRentData = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private By clientRentPeriod = By.className("Dropdown-control");
    private By scooterColor = By.id("black");

    private By courierComment = By.xpath("//input[@placeholder='Комментарий для курьера']");

    private By orderButton = By.className("Button_Button__ra12g");

    private By modalHeader = By.className("Order_ModalHeader__3FDaJ");

    public OrderScooterBlock(WebDriver driver) {
        this.driver = driver;
    }


    public void checkOrderScooterFirstWay(
            String name,
            String secondName,
            String address,
            long phone,
            String rentData,
            String comment
    ) {
        checkOrderScooter(firstOrderButton, name, secondName, address, phone, rentData, comment);
    }

    private void checkOrderScooter(By orderScooterButton,
                                   String name,
                                   String secondName,
                                   String address,
                                   long phone,
                                   String rentData,
                                   String comment) {
        clickOrderScooterButton(orderScooterButton);
        waitOrderContentVisible();

        setClientName(name);
        setClientSecondName(secondName);
        setClientAddress(address);
        setClientPhone(phone);
        setClientMetroStation();

        clickNextButton();

        waitRentContentVisible();
        setClientRentDate(rentData);
        setClientRentPeriod();
        setScooterColor();
        setCourierComment(comment);
        clickOrderButton();
        waitModalHeaderVisible();
        checkModalHeaderVisible();
    }

    private void clickOrderScooterButton(By orderScooterButton) {
        driver.findElement(orderScooterButton).click();
    }

    private void waitOrderContentVisible() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(orderContent));
    }

    private void setClientName(String name) {
        driver.findElement(clientName).sendKeys(name);
    }

    private void setClientSecondName(String secondName) {
        driver.findElement(clientSecondName).sendKeys(secondName);
    }

    private void setClientAddress(String address) {
        driver.findElement(clientAddress).sendKeys(address);
    }

    private void setClientMetroStation() {
        WebElement element = driver.findElement(clientMetroStation);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].value='Черкизовская';", element);
    }

    private void setClientPhone(long phone) {
        driver.findElement(clientPhone).sendKeys(String.valueOf(phone));
    }

    private void clickNextButton() {
        WebElement element = driver.findElement(nextButton);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        try{
            element.click();
        }catch (WebDriverException e){
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click()", element);
        }
    }

    private void waitRentContentVisible() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(rentContent));
    }

    private void setClientRentDate(String rentDate) {
        driver.findElement(clientRentData).sendKeys(rentDate);
    }

    private void setClientRentPeriod() {
        List<WebElement> li = driver.findElements(clientRentPeriod);;
        li.get(0).click();
    }

    private void setScooterColor() {
        driver.findElement(scooterColor).click();
    }

    private void setCourierComment(String comment) {
        driver.findElement(courierComment).sendKeys(comment);
    }

    private void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    private void waitModalHeaderVisible() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(modalHeader));
    }

    private void checkModalHeaderVisible(){
        Assert.assertTrue(driver.findElement(modalHeader).isDisplayed());
    }
}
