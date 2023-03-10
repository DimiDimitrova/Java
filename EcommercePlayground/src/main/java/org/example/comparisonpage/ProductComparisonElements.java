package org.example.comparisonpage;

import org.example.core.Driver;
import org.example.core.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class ProductComparisonElements {
    private Driver driver;

    public ProductComparisonElements(Driver driver) {
        this.driver = driver;
    }

    public Element modifiedMessage() {
        return driver.findElement(By.xpath("//*[contains(@class,'alert-success')]"));
    }

    public Element emptyCompareContext() {
        return driver.findElement(By.xpath("//*[@id='content']/p"));
    }

    public List<Element> compareTableRows() {
        return driver.findElements(By.xpath("//table[contains(@class,'table-responsive')]//tbody[1]/tr"));
    }

    public Element productName(String productName) {
        String locator = String.format("//*[@id='content']/table/tbody[1]/tr[1]//a/strong[contains(text(),'%s')]",
            productName.toString());
        return driver.findElement(By.xpath(locator));
    }

    public Element addToCart(String productName) {
        return driver.findElement(with(By.tagName("input")).below((WebElement) productName(productName)));
    }

    public Element removeButton(String productName) {
        return driver.findElement(with(By.tagName("a")).below((WebElement) addToCart(productName)));
    }
}