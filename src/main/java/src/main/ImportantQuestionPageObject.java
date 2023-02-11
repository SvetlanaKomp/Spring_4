package src.main;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ImportantQuestionPageObject implements SearchPage {
    private final WebDriver driver;
    private final List<String> expectedAccordionsText;

    @FindBy(className = "accordion__item")
    private List<WebElement> accordions;

    public ImportantQuestionPageObject(WebDriver driver, List<String> expectedAccordionsText) {
        this.driver = driver;
        this.expectedAccordionsText = expectedAccordionsText;
    }

    public void checkImportantQuestionsText() {
        Assert.assertEquals("Number of accordions must be the equals", expectedAccordionsText.size(), accordions.size());

        for (int i = 0; i < accordions.size(); ++i) {
            checkAccordion(i);
        }
    }

    @Override
    public void init(final WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    private void checkAccordion(int index) {
        By accordionButton = By.id("accordion__heading-" + index);
        By accordionPanel = By.id("accordion__panel-" + index);

        clickAccordionButton(accordionButton);
        waitForEnableAccordionPanel(accordionPanel);
        checkEnableAccordionPanel(accordionPanel);
        checkEqualsAccordionPanelText(accordionPanel, expectedAccordionsText.get(index));
    }

    private void clickAccordionButton(By accordionButton) {
        WebElement element = driver.findElement(accordionButton);

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    private void waitForEnableAccordionPanel(By accordionPanel) {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(accordionPanel));
    }

    private void checkEnableAccordionPanel(By accordionPanel) {
        Assert.assertTrue(driver.findElement(accordionPanel).isDisplayed());
    }

    private void checkEqualsAccordionPanelText(By accordionPanel, String expectedText) {
        Assert.assertEquals(expectedText, driver.findElement(accordionPanel).getText());
    }

}

