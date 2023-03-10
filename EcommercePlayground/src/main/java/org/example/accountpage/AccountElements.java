package org.example.accountpage;

import org.example.core.Driver;
import org.example.core.Element;
import org.example.enums.Navbar;
import org.openqa.selenium.By;

public class AccountElements {
    private Driver driver;

    public AccountElements(Driver driver) {
        this.driver = driver;
    }

    public Element warningMessage() {
        return driver.findElement(By.xpath("//*[@class='text-danger']"));
    }

    public Element accountNavbar(Navbar navbar) {
        String locator = String.format("//*[@id='column-right']/div/a[contains(@href,'%s')]", navbar.toString());
        return driver.findElement(By.xpath(locator));
    }
}