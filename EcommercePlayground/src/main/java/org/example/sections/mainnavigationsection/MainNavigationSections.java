package org.example.sections.mainnavigationsection;

import org.example.core.Driver;
import org.example.enums.MainMenu;

public class MainNavigationSections {
    private Driver driver;

    public MainNavigationSections(Driver webDriver) {
        this.driver = webDriver;
    }

    public MainNavigationSectionsElements elements() {
        return new MainNavigationSectionsElements(driver);
    }

    public void openMenu(MainMenu menu) {
        elements().selectMenu(menu).click();
    }

    public void moveToMainMenu(MainMenu menu) {
        elements().selectMenu(menu).moveTo();
    }
}