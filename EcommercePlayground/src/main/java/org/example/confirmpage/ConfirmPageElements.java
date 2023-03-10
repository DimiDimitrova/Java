package org.example.confirmpage;

import org.example.core.Driver;
import org.example.core.Element;
import org.example.enums.Item;
import org.example.enums.TablePrice;
import org.openqa.selenium.By;

import java.util.List;

public class ConfirmPageElements {
    private Driver driver;

    public ConfirmPageElements(Driver driver) {
        this.driver = driver;
    }

    public Element confirmOrderButton() {
        return driver.findElement(By.id("button-confirm"));
    }

    public Element paymentTable() {
        return driver.findElement(
            By.xpath("//*[text()='Payment Address']//following::*[@Class='card-body']"));
    }

    public Element totalSum() {
        return driver.findElement(
            By.xpath("//div[@id='content']//child::tfoot//strong[text()='Total:']/following::td"));
    }

    public Element editButton() {
        return driver.findElement(By.xpath("//*[contains(@class,'fa-caret-left')]//parent::a"));
    }

    public List<Element> productsContent() {
        return driver.findElements(By.xpath("//div[@id='content']//table/tbody/tr"));
    }

    public Element productQuantity(Item product) {
        String locator = String.format(
            "//div[@id='content']//table/tbody/tr/td[contains(text(),'%s')]//following::td[2]", product.toString());
        return driver.findElement(By.xpath(locator));
    }

    public Element confirmPrice(TablePrice price) {
        String locator = String.format("//div[@id='content']//child::tfoot//strong[contains(text(),'%s')]/following::td",
            price.toString());
        return driver.findElement(By.xpath(locator));
    }
}