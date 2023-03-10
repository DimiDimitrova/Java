package org.example.quickviewpage;

import org.example.core.Driver;
import org.example.core.Element;
import org.openqa.selenium.By;

public class QuickViewElements {
    private Driver driver;

    public QuickViewElements(Driver driver) {
        this.driver = driver;
    }

    public Element quickViewQuantityInput() {
        return driver.findElement(By.xpath("//*[contains(@class,'content-quantity')]//input"));
    }

    public Element decreaseQtyButton() {
        return driver.findElement(By.xpath("//button[contains(@aria-label,'Decrease quantity')]"));
    }

    public Element increaseQtyButton() {
        return driver.findElement(By.xpath("//button[contains(@aria-label,'Increase quantity')]"));
    }
}