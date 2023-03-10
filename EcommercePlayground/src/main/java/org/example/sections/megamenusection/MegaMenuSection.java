package org.example.sections.megamenusection;

import org.example.core.Driver;

public class MegaMenuSection {
    private Driver driver;

    public MegaMenuSection(Driver driver) {
        this.driver = driver;
    }

    public MegaMenuSectionElements elements() {
        return new MegaMenuSectionElements(driver);
    }

    public MegaMenuSectionAssertions assertions() {
        return new MegaMenuSectionAssertions(driver);
    }
}