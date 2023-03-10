package org.example.editaccountpage;

import org.example.core.Driver;
import org.example.core.Element;
import org.openqa.selenium.By;

public class EditAccountElements {
    private Driver driver;

    public EditAccountElements(Driver driver) {
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

    public Element continueButton() {
        return driver.findElement(By.xpath("//input[@value='Continue']"));
    }
}