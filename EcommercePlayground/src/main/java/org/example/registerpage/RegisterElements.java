package org.example.registerpage;

import org.example.core.Driver;
import org.example.core.Element;
import org.openqa.selenium.By;

public class RegisterElements {
    private Driver driver;

    public RegisterElements(Driver driver) {
        this.driver = driver;
    }

    public Element firstNameInput() {
        return driver.findElement(By.id("input-firstname"));
    }

    public Element lastNameInput() {
        return driver.findElement(By.id("input-lastname"));
    }

    public Element emailInput() {
        return driver.findElement(By.id("input-email"));
    }

    public Element telephoneInput() {
        return driver.findElement(By.id("input-telephone"));
    }

    public Element passwordInput() {
        return driver.findElement(By.id("input-password"));
    }

    public Element confirmPassword() {
        return driver.findElement(By.id("input-confirm"));
    }

    public Element agreeCheckbox() {
        return driver.findElement(By.xpath("//*[@id='input-agree']//parent::div"));
    }

    public Element continueButton() {
        return driver.findElement(By.xpath("//input[@value='Continue']"));
    }

    public Element agreeWithPrivacyMessage() {
        return driver.findElement(By.xpath(
                "//div[@id='account-register']//div[contains(text(), 'Warning: You must agree to the Privacy Policy!')]"));
    }

    public Element emailWarningMessage() {
        return driver.findElement(By.xpath("//*[contains(@class,'fa-exclamation-circle')]"));
    }
}