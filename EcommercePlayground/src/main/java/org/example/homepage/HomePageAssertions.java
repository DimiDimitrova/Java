package org.example.homepage;

import org.example.ApplicationMessages;
import org.example.core.Driver;
import org.example.enums.HomePageModuleTitle;
import org.example.enums.PageTitle;
import org.example.sections.breadcrumbsection.BreadcrumbSection;
import org.junit.jupiter.api.Assertions;

public class HomePageAssertions {
    private Driver driver;

    public HomePageAssertions(Driver driver) {
        this.driver = driver;
    }

    private BreadcrumbSection breadcrumbSection() {
        return new BreadcrumbSection(driver);
    }

    private HomePageElements homePageElements() {
        return new HomePageElements(driver);
    }

    public void assertCorrectPageIsOpen(String page) {
        String temp = page.split(" ")[0];
        Assertions.assertEquals(temp, breadcrumbSection().elements().activePageTitle().getText(),
            String.format(ApplicationMessages.PAGE_ERROR, temp));
    }

    public void assertModuleInformationPresent(HomePageModuleTitle module) {
        Assertions.assertTrue(homePageElements().informationForModule(module).isDisplayed(),
            String.format(ApplicationMessages.HOME_PAGE_MODULE_ERROR,
                homePageElements().informationForModule(module).getText()));
    }

    public void assertHomePageIsLoaded() {
        Assertions.assertTrue(driver.getCurrentUrl().endsWith("common/home"),
            String.format(ApplicationMessages.PAGE_ERROR, PageTitle.HOME.toString()));
    }
}