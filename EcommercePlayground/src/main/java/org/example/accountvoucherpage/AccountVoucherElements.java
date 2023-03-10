package org.example.accountvoucherpage;

import org.example.core.Driver;
import org.example.core.Element;
import org.openqa.selenium.By;

public class AccountVoucherElements {
    private Driver driver;

    public AccountVoucherElements(Driver driver) {
        this.driver = driver;
    }

    public Element recipientName() {
        return driver.findElement(By.id("input-to-name"));
    }

    public Element recipientMail() {
        return driver.findElement(By.id("input-to-email"));
    }

    public Element agreeCheckbox() {
        return driver.findElement(By.xpath("//div[@class='float-right']/input[@type='checkbox']"));
    }

    public Element continueButton() {
        return driver.findElement(By.xpath("//input[contains(@class,'btn-primary')]"));
    }

    public Element amount() {
        return driver.findElement(By.id("input-amount"));
    }

    public Element fromName() {
        return driver.findElement(By.id("input-from-name"));
    }

    public Element errorMessage() {
        return driver.findElement(By.xpath("//*[@class='text-danger']"));
    }

    public Element giftCertificateTheme(String theme) {
        String locator = String.format("//label[contains(text(),'%s')]/input", theme);
        return driver.findElement(By.xpath(locator));
    }
}
