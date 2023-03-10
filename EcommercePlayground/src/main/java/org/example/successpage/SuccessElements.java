package org.example.successpage;

import org.example.core.Driver;
import org.example.core.Element;
import org.openqa.selenium.By;

public class SuccessElements {
    private Driver driver;

    public SuccessElements(Driver driver) {
        this.driver = driver;
    }

    public Element continueButton() {
        return driver.findElement(By.xpath("//div[@id='content']//a[contains(@href,'common/home')]"));
    }

    public Element alertMessage() {
        return driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]"));
    }
}