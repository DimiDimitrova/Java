package org.example.sections.myaccountdropdownsections;

import org.example.core.Driver;

public class MyAccountDropDownSections {
    private Driver driver;

    public MyAccountDropDownSections(Driver driver) {
        this.driver = driver;
    }

    public MyAccountDropDownSectionsElements elements() {
        return new MyAccountDropDownSectionsElements(driver);
    }
}