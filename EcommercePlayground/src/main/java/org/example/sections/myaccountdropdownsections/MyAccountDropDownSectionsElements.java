package org.example.sections.myaccountdropdownsections;

import org.example.core.Driver;
import org.example.core.Element;
import org.example.enums.MyAccountDropDown;
import org.openqa.selenium.By;

public class MyAccountDropDownSectionsElements {
    private Driver driver;

    public MyAccountDropDownSectionsElements(Driver driver) {
        this.driver = driver;
    }

    public Element myAccountMenu(MyAccountDropDown menu) {
        String locator = String.format(
            "//div[@id='main-navigation']//li[contains(@class,'dropdown-hoverable')]//a[contains(@href,'%s')]",
            menu.toString());
        return driver.findElement(By.xpath(locator));
    }
}
