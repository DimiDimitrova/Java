package org.example.sections.breadcrumbsection;

import org.example.core.Driver;

public class BreadcrumbSection {
    private final Driver driver;

    public BreadcrumbSection(Driver driver) {
        this.driver = driver;
    }
    public BreadcrumbSectionElements elements() {
        return new BreadcrumbSectionElements(driver);
    }

    public  BreadcrumbSectionAssertions assertions(){
        return new BreadcrumbSectionAssertions(driver);
    }
}
