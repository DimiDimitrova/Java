package org.example.cartpage;

import org.example.core.Driver;
import org.example.core.Element;
import org.example.enums.CartAccordion;
import org.example.enums.Country;
import org.example.enums.TablePrice;
import org.openqa.selenium.By;

import java.util.List;

public class CartPageElements {
    private Driver driver;

    public CartPageElements(Driver driver) {
        this.driver = driver;
    }

    public Element cartButton() {
        return driver.findElement(By.xpath("//a[contains(@href,'checkout/cart')]"));
    }

    public Element checkoutButton() {
        return driver.findElement(By.xpath("//a[contains(text(),'Checkout')]"));
    }

    public Element checkoutButtonInCart() {
        return driver.findElement(By.xpath("//*[text()=' Checkout']//parent::a"));
    }

    public Element continueShoppingButton() {
        return driver.findElement(By.xpath("//a[contains(text(),'Continue Shopping')]"));
    }

    public Element shoppingCartIsEmptyMessage() {
        return driver.findElement(By.xpath("//h1[contains(@Class,'page-title')]/following-sibling::p"));
    }

    public List<Element> cartRowsContent() {
        return driver.findElements(By.xpath("//div[@class='table-responsive']//tbody//tr"));
    }

    public Element viewCartButton() {
        return driver.findElement(By.xpath("//a[contains(text(),'View Cart')]"));
    }

    public Element countryShippingTaxes() {
        return driver.findElement(By.id("input-country"));
    }

    public Element regionStateShippingTaxes() {
        return driver.findElement(By.id("input-zone"));
    }

    public Element postCodeSatateShippingTaxes() {
        return driver.findElement(By.id("input-postcode"));
    }

    public Element getQuotesButton() {
        return driver.findElement(By.id("button-quote"));
    }

    public Element flatRate() {
        return driver.findElement(By.xpath("//div[@class='form-check']//input"));
    }

    public Element flatRateInformation() {
        return driver.findElement(By.xpath("//div[@class='form-check']//label"));
    }

    public Element applyShippingButton() {
        return driver.findElement(By.id("button-shipping"));
    }

    public Element regionInvalidMessage() {
        return driver.findElement(By.xpath("//*[@id='input-zone']//following::div[@class='invalid-feedback']"));
    }

    public Element postCodeInvalidMessage() {
        return driver.findElement(By.xpath("//*[@id='input-postcode']//following::div[@class='invalid-feedback']"));
    }

    public Element voucherInput() {
        return driver.findElement(By.id("input-voucher"));
    }

    public Element applyGiftCertificate() {
        return driver.findElement(By.id("button-voucher"));
    }

    public Element giftError() {
        return driver.findElement(By.xpath("//*[contains(@class,'fa-exclamation-circle')]"));
    }

    public List<Element> tableProductQuantity() {
        return driver.findElements(By.xpath("//div[@class='table-responsive']//tbody//tr//div/input"));
    }

    public List<Element> tableProductUnitPrice() {
        return driver.findElements(By.xpath("//div[@class='table-responsive']//tbody//tr//td[@class='text-right'][1]"));
    }

    public Element ecoTax() {
        return driver.findElement(By.xpath("//table[contains(@class,'m-0')]//td[contains(text(),'Eco')]"));
    }

    public Element vatTax() {
        return driver.findElement(By.xpath("//table[contains(@class,'m-0')]//td[contains(text(),'VAT')]"));
    }

    public Element regionOption(String option) {
        String locator = String.format("//*[@id='input-zone']/option[contains(@value,'%s')]", option);
        return driver.findElement(By.xpath(locator));
    }

    public Element regionOption() {
        String locator = String.format("//*[@id='input-zone']/option[contains(@value,'%s)]", "None");
        return driver.findElement(By.xpath(locator));
    }

    public Element countryOption(Country country) {
        String locator = String.format("//*[@id='input-country']/option[contains(text(),'%s')]", country.toString());
        return driver.findElement(By.xpath(locator));
    }

    public Element updateButton(String productName) {
        String locator = String.format(
            "//tbody//td/a[contains(text(),'%s')]//following::i[contains(@class,'fa-sync-alt')]", productName);
        return driver.findElement(By.xpath(locator));
    }

    public Element unitPrice(String product) {
        String locator = String.format(
            "//*[contains(@class,'table-bordered')]//a[contains(text(),'%s')]//following::td[3]", product);
        return driver.findElement(By.xpath(locator));
    }

    public Element total(String product) {
        String locator = String.format(
            "//*[contains(@class,'table-bordered')]//a[contains(text(),'%s')]//following::td[4]", product);
        return driver.findElement(By.xpath(locator));
    }

    public Element removeButton(String product) {
        String locator = String.format(
            "//tbody//td/a[contains(text(),'%s')]//following::i[contains(@class,'fa-times-circle')]", product);
        return driver.findElement(By.xpath(locator));
    }

    public Element quantityField(String product) {
        String locator = String.format(
            "//tbody//td/a[contains(text(),'%s')]//following::div[@class = 'input-group flex-nowrap']/input", product
        );
        return driver.findElement(By.xpath(locator));
    }

    public Element openAccordion(CartAccordion cartAccordion) {
        String locator = String.format(
            "//*[contains(text(),'%s')]/*[contains(@Class,'fa-plus')]", cartAccordion.toString());
        return driver.findElement(By.xpath(locator));
    }

    public Element selectCartPriceInformation(TablePrice name) {
        String locator = String.format("//table[contains(@class,'m-0')]//td[contains(text(),'%s')]//following::strong",
            name.toString());
        return driver.findElement(By.xpath(locator));
    }

    public Element selectCartTotalInfo(TablePrice price) {
        String locator = String.format(
            "//table[contains(@class,'table-bordered')]//td[text()='%s']//following-sibling::td[@class='text-right']/strong",
            price.toString());
        return driver.findElement(By.xpath(locator));
    }
}