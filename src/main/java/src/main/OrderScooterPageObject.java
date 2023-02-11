package src.main;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class OrderScooterPageObject implements SearchPage {
    private final WebDriver driver;

    @FindBy(xpath = "//button[text()='Заказать']")
    private WebElement firstOrderButton;

    @FindBy(className = "Order_Content__bmtHS")
    private WebElement orderContent;

    @FindBy(xpath = "//input[@placeholder='* Имя']")
    private WebElement clientName;
    @FindBy(xpath = "//input[@placeholder='* Фамилия']")
    private WebElement clientSecondName;
    @FindBy(xpath = "//input[@placeholder='* Адрес: куда привезти заказ']")
    private WebElement clientAddress;
    @FindBy(xpath = "//input[@placeholder='* Станция метро']")
    private WebElement clientMetroStation;
    @FindBy(xpath = "//input[@placeholder='* Телефон: на него позвонит курьер']")
    private WebElement clientPhone;
    @FindBy(xpath = "//button[text()='Далее']")
    private WebElement nextButton;

    @FindBy(className = "Order_Content__bmtHS")
    private WebElement rentContent;
    @FindBy(xpath = "//input[@placeholder='* Когда привезти самокат']")
    private WebElement clientRentData;
    @FindBy(className = "Dropdown-control")
    private List<WebElement> clientRentPeriod;
    @FindBy(id = "black")
    private WebElement scooterColor;
    @FindBy(xpath = "//input[@placeholder='Комментарий для курьера']")
    private WebElement courierComment;
    @FindBy(className = "Button_Button__ra12g")
    private WebElement orderButton;
    @FindBy(className = "Order_ModalHeader__3FDaJ")
    private WebElement modalHeader;

    public OrderScooterPageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void init(WebDriver driver) {
        PageFactory.initElements(driver, this);
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

    private void checkOrderScooter(WebElement orderScooterButton,
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

    private void clickOrderScooterButton(WebElement orderScooterButton) {
        orderScooterButton.click();
    }

    private void waitOrderContentVisible() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(orderContent));
    }

    private void setClientName(String name) {
        clientName.sendKeys(name);
    }

    private void setClientSecondName(String secondName) {
        clientSecondName.sendKeys(secondName);
    }

    private void setClientAddress(String address) {
        clientAddress.sendKeys(address);
    }

    private void setClientMetroStation() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].value='Черкизовская';", clientMetroStation);
    }

    private void setClientPhone(long phone) {
        clientPhone.sendKeys(String.valueOf(phone));
    }

    private void clickNextButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextButton);

        try {
            nextButton.click();
        } catch (WebDriverException e) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click()", nextButton);
        }
    }

    private void waitRentContentVisible() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(rentContent));
    }

    private void setClientRentDate(String rentDate) {
        clientRentData.sendKeys(rentDate);
    }

    private void setClientRentPeriod() {
        clientRentPeriod.get(0).click();
    }

    private void setScooterColor() {
        scooterColor.click();
    }

    private void setCourierComment(String comment) {
        courierComment.sendKeys(comment);
    }

    private void clickOrderButton() {
        orderButton.click();
    }

    private void waitModalHeaderVisible() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(modalHeader));
    }

    private void checkModalHeaderVisible() {
        Assert.assertTrue(modalHeader.isDisplayed());
    }
}
