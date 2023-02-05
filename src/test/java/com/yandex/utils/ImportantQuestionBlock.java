package com.yandex.utils;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ImportantQuestionBlock {
    private final WebDriver driver;
    private final List<String> expectedAccordionsText;

    private By accordions = By.className("accordion__item");

    public ImportantQuestionBlock(WebDriver driver, List<String> expectedAccordionsText) {
        this.driver = driver;
        this.expectedAccordionsText = expectedAccordionsText;
    }

    public void checkImportantQuestionsText() {
        List<WebElement> elements = driver.findElements(accordions);

        Assert.assertEquals("Number of accordions must be the equals", expectedAccordionsText.size(), elements.size());

        for (int i = 0; i < elements.size(); ++i) {
            checkAccordion(i);
        }
    }

    private void checkAccordion(int index) {
        By accordionButton = By.id("accordion__heading-" + index);
        By accordionPanel = By.id("accordion__panel-" + index);

        clickAccordionButton(accordionButton);
        waitForEnableAccordionPanel(accordionPanel);
        checkEnableAccordionPanel(accordionPanel);
        checkEqualsAccordionPanelText(accordionPanel, expectedAccordionsText.get(index));
    }

    private void clickAccordionButton(By accordionButton){
        WebElement element = driver.findElement(accordionButton);

        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }

    private void waitForEnableAccordionPanel(By accordionPanel){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(accordionPanel));
    }

    private void checkEnableAccordionPanel(By accordionPanel){
        Assert.assertTrue(driver.findElement(accordionPanel).isDisplayed());
    }

    private void checkEqualsAccordionPanelText(By accordionPanel, String expectedText){
        Assert.assertEquals(expectedText, driver.findElement(accordionPanel).getText());
    }

}

