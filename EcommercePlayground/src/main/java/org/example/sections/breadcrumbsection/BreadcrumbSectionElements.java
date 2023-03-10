package org.example.sections.breadcrumbsection;

import org.example.core.Driver;
import org.example.core.Element;
import org.openqa.selenium.By;

public class BreadcrumbSectionElements {
    private Driver driver;

    public BreadcrumbSectionElements(Driver browser) {
        this.driver = browser;
    }

    public Element pageTitle() {
        return driver.findElement(By.xpath("//*[contains(@class,'page-title')]"));
    }

    public Element activePageTitle() {
        return driver.findElement(By.xpath("//li[@aria-current='page']"));
    }
}