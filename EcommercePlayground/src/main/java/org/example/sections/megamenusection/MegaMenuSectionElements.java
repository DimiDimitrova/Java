package org.example.sections.megamenusection;

import org.example.core.Driver;
import org.example.core.Element;
import org.openqa.selenium.By;

public class MegaMenuSectionElements {
    private Driver driver;

    public MegaMenuSectionElements(Driver driver) {
        this.driver = driver;
    }

    public Element searchCategoryHeader() {
        return driver.findElement(By.xpath("//*[contains(@class,'content-title')]/h1"));
    }
}