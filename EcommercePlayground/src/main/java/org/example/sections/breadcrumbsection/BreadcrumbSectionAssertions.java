package org.example.sections.breadcrumbsection;

import org.example.core.Driver;
import org.junit.jupiter.api.Assertions;

import static org.example.ApplicationMessages.PAGE_ERROR;

public class BreadcrumbSectionAssertions {
    private Driver driver;

    public BreadcrumbSectionAssertions(Driver browser) {
        this.driver = browser;
    }

    protected BreadcrumbSectionElements breadcrumbSectionElements() {
        return new BreadcrumbSectionElements(driver);
    }

    public void assertMenuIsOpen(String expectedResult) {
        Assertions.assertEquals(breadcrumbSectionElements().activePageTitle().getText(), expectedResult, PAGE_ERROR);
    }
}