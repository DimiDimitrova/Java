package org.example.sections.mainnavigationsection;

import org.example.core.Driver;
import org.example.core.Element;
import org.example.enums.MainMenu;
import org.openqa.selenium.By;

public class MainNavigationSectionsElements {
    private Driver driver;

    public MainNavigationSectionsElements(Driver browser) {
        this.driver = browser;
    }

    public Element selectMenu(MainMenu menu) {
        String locator = String.format("//ul[@class='navbar-nav horizontal']//a[contains(@href,'%s')]", menu.toString());
        return driver.findElement(By.xpath(locator));
    }
}
