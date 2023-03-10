package org.example.accountorderpage;

import org.example.core.Driver;
import org.example.core.Element;
import org.openqa.selenium.By;

public class AccountOrderElements {
    private Driver driver;

    public AccountOrderElements(Driver driver) {
        this.driver = driver;
    }

    public Element orderTable() {
        return driver.findElement(By.xpath("//*[@id='content']//table[contains(@class,'table-bordered')]"));
    }

    public Element emptyHistory() {
        return driver.findElement(By.xpath("//div[@id='content']//p"));
    }
}