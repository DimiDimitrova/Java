package org.example.core;

import org.openqa.selenium.By;

import java.util.List;

public abstract class Element {
    public abstract By getBy();
    public abstract String getText();
    public abstract Boolean isEnabled();
    public abstract Boolean isDisplayed();
    public abstract void typeText(String text);
    public abstract void click();
    public abstract String getAttribute(String attributeName);
    public abstract List<Element> findElements(By locator);
    public abstract Element findElement(By locator);
    public abstract void moveTo();
}