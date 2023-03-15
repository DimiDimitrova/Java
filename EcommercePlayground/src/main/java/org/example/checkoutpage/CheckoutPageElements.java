package org.example.checkoutpage;

import org.example.core.Driver;
import org.example.core.Element;
import org.example.enums.Account;
import org.example.enums.Country;
import org.example.enums.Item;
import org.example.enums.TablePrice;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class CheckoutPageElements {
    private Driver driver;

    public CheckoutPageElements(Driver driver) {
        this.driver = driver;
    }

    public Element firstNameField() {
        return driver.findElement(By.id("input-payment-firstname"));
    }

    public Element lastNameField() {
        return driver.findElement(By.id("input-payment-lastname"));
    }

    public Element emailField() {
        return driver.findElement(By.id("input-payment-email"));
    }

    public Element telephoneField() {
        return driver.findElement(By.id("input-payment-telephone"));
    }

    public Element billingPaymentFirstAddressField() {
        return driver.findElement(By.id("input-payment-address-1"));
    }

    public Element billingPaymentCityField() {
        return driver.findElement(By.id("input-payment-city"));
    }

    public Element billingPaymentPostCodeField() {
        return driver.findElement(By.id("input-payment-postcode"));
    }

    public Element billingPaymentCountryDropDown() {
        return driver.findElement(By.id("input-payment-country"));
    }

    public Element billingPaymentRegionField() {
        return driver.findElement(By.id("input-payment-zone"));
    }

    public Element continueButton() {
        return driver.findElement(By.id("button-save"));
    }

    public Element paymentPassword() {
        return driver.findElement(By.id("input-payment-password"));
    }

    public Element paymentConfirmPassword() {
        return driver.findElement(By.id("input-payment-confirm"));
    }

    public Element termsCheckbox() {
        return driver.findElement(By.xpath("//*[@id='input-agree']//parent::div//child::label"));
    }

    public Element privacyPoliceCheckbox() {
        return driver.findElement(By.xpath("//*[@id='input-account-agree']//parent::div//child::label"));
    }

    public Element loginEmail() {
        return driver.findElement(By.id("input-login-email"));
    }

    public Element loginPassword() {
        return driver.findElement(By.id("input-login-password"));
    }

    public Element loginButton() {
        return driver.findElement(By.id("button-login"));
    }

    public Element newPaymentAddress() {
        return driver.findElement(By.xpath("//*[@id='input-payment-address-new']//parent::div//child::label"));
    }

    public List<Element> checkoutTableRows() {
        return driver.findElements(By.xpath("//div[@class='table-responsive']//tbody//tr"));
    }

    public List<Element> tableProductQuantity() {
        return driver.findElements(By.xpath("//div[@class='table-responsive']//tbody//tr//div"));
    }

    public Element total() {
        return driver.findElement(By.xpath(
                "//table[@id='checkout-total']//td[text()='Total:']//following::strong"));
    }

    public Element firstNameError() {
        return driver.findElement(By.xpath(
                "//input[@id='input-payment-firstname']//following::div[contains(@class,'invalid-feedback')]"));
    }

    public Element lastNameError() {
        return driver.findElement(By.xpath(
                "//input[@id='input-payment-lastname']//following::div[contains(@class,'invalid-feedback')]"));
    }

    public Element emailError() {
        return driver.findElement(By.xpath(
                "//input[@id='input-payment-email']//following::div[contains(@class,'invalid-feedback')]"));
    }

    public Element telephoneError() {
        return driver.findElement(By.xpath(
                "//input[@id='input-payment-telephone']//following::div[contains(@class,'invalid-feedback')]"));
    }

    public Element passwordError() {
        return driver.findElement(By.xpath(
                "//input[@id='input-payment-password']//following::div[contains(@class,'invalid-feedback')]"));
    }

    public Element passwordConfirmationError() {
        return driver.findElement(By.xpath(
                "//input[@id='input-payment-confirm']//following::div[contains(@class,'invalid-feedback')]"));
    }

    public Element addressError() {
        return driver.findElement(By.xpath(
                "//input[@id='input-payment-address-1']//following::div[contains(@class,'invalid-feedback')]"));
    }

    public Element termsWarningMessage() {
        return driver.findElement(By.xpath("//*[@id='form-checkout']/div"));
    }

    public Element cityError() {
        return driver.findElement(By.xpath(
                "//input[@id='input-payment-city']//following::div[contains(@class,'invalid-feedback')]"));
    }

    public Element countryError() {
        return driver.findElement(By.xpath(
                "//select[@id='input-payment-country']//following::div[contains(@class,'invalid-feedback')]"));
    }

    public Element postCodeError() {
        return driver.findElement(By.xpath(
                "//input[@id='input-payment-postcode']//following::div[contains(@class,'invalid-feedback')]"));
    }

    public Element accountOption(Account option) {
        String locator = String.format("//*[@id='input-account-%s']/following-sibling::label", option.toString());
        return driver.findElement(By.xpath(locator));
    }

    public Element selectCheckoutTotalInfo(TablePrice checkout) {
        String locator = String.format(
            "//table[@id='checkout-total']//td[contains(text(),'%s')]//following::strong", checkout.toString());
        return driver.findElement(By.xpath(locator));
    }

    public Element getVatTax() {
        String locator = String.format("//table[@id='checkout-total']//td[contains(text(),'%s')]",
            TablePrice.VAT.toString());
        return driver.findElement(By.xpath(locator));
    }

    public Element getEcoTax() {
        String locator = String.format("//table[@id='checkout-total']//td[contains(text(),'%s')]",
            TablePrice.ECO_TAX.toString());
        return driver.findElement(By.xpath(locator));
    }

    public Element unitPrice(Item product) {
        String locator = String.format(
            "//a[contains(text(),'%s')]//following::td//button[@data-original-title='Update']//following::td[1]",
            product.toString());
        return driver.findElement(By.xpath(locator));
    }

    public Element totalOnProduct(Item product) {
        String locator = String.format(
            "//a[contains(text(),'%s')]//following::td//button[@data-original-title='Update']//following::td[2]",
            product.toString());
        return driver.findElement(By.xpath(locator));
    }

    public Element quantityField(Item product) {
        String locator = String.format(
            "//div[@id='checkout-cart']//td/a[text()='%s']//following::td[@class='text-left']//input",
            product.toString());
        return driver.findElement(By.xpath(locator));
    }

    public Element updateButton(Item productName) {
        String locator = String.format(
            "//tbody//td/a[contains(text(),'%s')]//following::i[contains(@class,'fa-sync-alt')]",
            productName.toString());
        return driver.findElement(By.xpath(locator));
    }

    public Element removeButton(Item product) {
        return driver.findElement(with(By.tagName("button")).toRightOf((WebElement) updateButton(product)));
    }

    public Element countryOption(Country country) {
        String locator = String.format("//*[@id='input-payment-country']/option[contains(text(),'%s')]", country.toString());
        return driver.findElement(By.xpath(locator));
    }

    public Element regionOption(String option) {
        String locator = String.format("//*[@id='input-payment-zone']/option[contains(@value,'%s')]", option);
        return driver.findElement(By.xpath(locator));
    }
}