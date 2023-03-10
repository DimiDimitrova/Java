package org.example.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WebCoreElement extends Element {
    private WebDriver webDriver;
    private WebElement webElement;
    private WebDriverWait webDriverWait;
    private By by;

    public WebCoreElement(WebDriver webDriver, WebElement webElement, By by) {
        this.webDriver = webDriver;
        this.webElement = webElement;
        this.by = by;
    }

    @Override
    public By getBy() {
        return by;
    }

    @Override
    public String getText() {
        return webElement.getText();
    }

    @Override
    public Boolean isEnabled() {
        return webElement.isEnabled();
    }

    @Override
    public Boolean isDisplayed() {
        return webElement.isDisplayed();
    }

    @Override
    public void typeText(String text) {
        webElement.clear();
        webElement.sendKeys(text);
    }

    @Override
    public void click() {
        waitToBeClickable(by);
        webElement.click();
    }
     @Override
    public void moveTo() {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(webElement).perform();
    }

    public Element findElement(By locator) {
        webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        WebElement webElement =
            webDriverWait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(getBy(), locator));

        return new WebCoreElement(webDriver, webElement, locator);
    }

    @Override
    public List<Element> findElements(By locator) {
        webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        // visibilityOfNestedElementsLocatedBy
        var nativeWebElements =
            webDriverWait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(getBy(), locator));
        var elements = new ArrayList<Element>();
        for (var nativeWebElement : nativeWebElements) {
            Element element = new WebCoreElement(webDriver, nativeWebElement, locator);
            elements.add(element);
        }
        return elements;
    }

    @Override
    public String getAttribute(String attributeName) {
        return webElement.getAttribute(attributeName);
    }

    private void waitToBeClickable(By by) {
        var webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
    }
}
