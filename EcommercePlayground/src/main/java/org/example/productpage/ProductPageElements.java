package org.example.productpage;

import org.example.core.Driver;
import org.example.core.Element;
import org.example.enums.ExtraProductContent;
import org.example.enums.Item;
import org.openqa.selenium.By;

public class ProductPageElements {
    private Driver driver;

    public ProductPageElements(Driver driver) {
        this.driver = driver;
    }

    public Element productName() {
        return driver.findElement(By.xpath("//div[@id='product-quick-view']//h1"));
    }

    public Element product() {
        return driver.findElement(By.xpath("//div[@id='product-product']//h1"));
    }

    public Element productImage() {
        return driver.findElement(By.xpath("//div[contains(@class,'image-gallery')]//img"));
    }

    public Element productPrice() {
        return driver.findElement(By.xpath("//div[@class='price']/h3"));
    }

    public Element compareButton() {
        return driver.findElement(By.xpath("//button[contains(text(),'Compare this Product')]"));
    }

    public Element addToCartItemButton() {
        return driver.findElement(By.xpath("//div[contains(@class,'entry-row')]//button[contains(@class,'button-cart')]"));
    }

    public Element buyNowButton() {
        return driver.findElement(By.xpath(
                "//button[contains(@class,'button-buynow')]"));
    }

    public Element compareLink() {
        return driver.findElement(By.xpath(
                "//*[contains(text(),'Success: You have added ')]//a[contains(@href,'compare')]"));
    }

    public Element listViewButton() {
        return driver.findElement(By.id("list-view"));
    }

    public Element brand() {
        return driver.findElement(By.xpath("//span[contains(text(),'Brand')]//following-sibling::a"));
    }

    public Element quickViewButton(Item product) {
        String locator = String.format("//*[@class='product-action']//button[contains(@class,'quick-view-%d')]",
                product.getId());
        return driver.findElement(By.xpath(locator));
    }

    public Element imageItem(Item productName) {
        String locator = String.format("//div[@class='carousel-item active']//*[@title='%s']", productName.toString());
        return driver.findElement(By.xpath(locator));
    }

    public Element productExtraContent(ExtraProductContent label) {
        String locator = String.format("//*[contains(@class,'content-extra')]//span[contains(text(),'%s')]//following::span",
                label.toString());
        return driver.findElement(By.xpath(locator));
    }
}