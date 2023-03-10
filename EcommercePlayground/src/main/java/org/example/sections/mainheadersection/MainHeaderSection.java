package org.example.sections.mainheadersection;

import org.example.core.Driver;

public class MainHeaderSection {
    private Driver driver;

    public MainHeaderSection(Driver driver) {
        this.driver = driver;
    }

    public MainHeaderSectionElements elements(){
        return new MainHeaderSectionElements(driver);
    }
}