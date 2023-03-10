package org.example.sections.mainheadersection;

import org.example.core.Driver;
import org.example.core.Element;
import org.example.enums.MainHeader;
import org.openqa.selenium.By;

public class MainHeaderSectionElements {
    private Driver driver;

    public MainHeaderSectionElements(Driver driver) {
        this.driver = driver;
    }

    public Element mainHeaderNavigation(MainHeader link) {
        String locator = String.format("//div[@id='main-header']//a[contains(@href,'%s')]", link);
        return driver.findElement(By.xpath(locator));
    }
}