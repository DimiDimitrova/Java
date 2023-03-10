package org.example.loginpage;

import org.example.core.Driver;
import org.example.core.Element;
import org.openqa.selenium.By;

public class LoginElements {
    private Driver driver;

    public LoginElements(Driver driver) {
        this.driver = driver;
    }

    public Element loginWarningMessage() {
        return driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]"));
    }

    public Element emailInput() {
        return driver.findElement(By.id("input-email"));
    }

    public Element passwordInput() {
        return driver.findElement(By.id("input-password"));
    }

    public Element loginButton() {
        return driver.findElement(By.xpath("//div//input[@value='Login']"));
    }
}