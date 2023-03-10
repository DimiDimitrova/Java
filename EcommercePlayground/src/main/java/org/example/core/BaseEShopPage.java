package org.example.core;

import org.example.sections.breadcrumbsection.BreadcrumbSection;
import org.example.sections.mainheadersection.MainHeaderSection;
import org.example.sections.mainnavigationsection.MainNavigationSections;
import org.example.sections.megamenusection.MegaMenuSection;
import org.example.sections.myaccountdropdownsections.MyAccountDropDownSections;

public abstract class BaseEShopPage {
    protected final Driver driver;

    public BaseEShopPage(Driver driver) {
        this.driver = driver;
    }

    public BreadcrumbSection breadcrumbSection() {
        return new BreadcrumbSection(driver);
    }

    public MainNavigationSections mainNavigationSections() {
        return new MainNavigationSections(driver);
    }

    public MyAccountDropDownSections myAccountDropDownSections() {
        return new MyAccountDropDownSections(driver);
    }

    public MainHeaderSection mainHeaderSection() {
        return new MainHeaderSection(driver);
    }

    public MegaMenuSection megaMenuSection(){
        return new MegaMenuSection(driver);
    }
}