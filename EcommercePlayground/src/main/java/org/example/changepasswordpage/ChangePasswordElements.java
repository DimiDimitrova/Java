package org.example.changepasswordpage;

import org.example.core.Driver;
import org.example.core.Element;
import org.openqa.selenium.By;

public class ChangePasswordElements {
    private Driver driver;

    public ChangePasswordElements(Driver driver) {
        this.driver = driver;
    }

    public Element passwordInput() {
        return driver.findElement(By.id("input-password"));
    }

    public Element confirmPasswordInput() {
        return driver.findElement(By.id("input-confirm"));
    }

    public Element continueButton() {
        return driver.findElement(By.xpath("//*[@class='buttons clearfix']//input"));
    }
}