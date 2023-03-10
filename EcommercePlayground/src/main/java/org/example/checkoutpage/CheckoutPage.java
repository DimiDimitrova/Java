package org.example.checkoutpage;

import org.example.core.BaseEShopPage;
import org.example.core.Driver;
import org.example.enums.Account;
import org.example.enums.Item;
import org.example.enums.TablePrice;
import org.example.extensions.PriceConvertingExtensions;
import org.example.models.PaymentAddressInfo;
import org.example.models.PersonInfo;
import org.example.models.ProductInfo;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class CheckoutPage extends BaseEShopPage {
    public CheckoutPage(Driver driver) {
        super(driver);
    }

    public CheckoutPageElements elements() {
        return new CheckoutPageElements(driver);
    }

    public CheckoutPageAssertions assertions() {
        return new CheckoutPageAssertions(driver);
    }

    public String url = "https://ecommerce-playground.lambdatest.io/index.php?route=checkout/checkout";

    public static double totalPrice = 0;

    public void fillPaymentAddress(PaymentAddressInfo billingAddress) {
        elements().billingPaymentFirstAddressField().typeText(billingAddress.getAddress());
        elements().billingPaymentCityField().typeText(billingAddress.getCity());
        elements().billingPaymentPostCodeField().typeText(billingAddress.getPostCode());
        elements().billingPaymentCountryDropDown().click();
        elements().countryOption(billingAddress.getCountry()).click();
        elements().billingPaymentRegionField().click();
        driver.waitForAjax();

        try {
            billingAddress.region = elements().regionOption("1").getText();
            elements().regionOption("1").click();
        } catch (Exception ex) {
            billingAddress.region = elements().regionOption("0").getText();
            elements().regionOption("0").click();
        }
    }

    public void openCheckout() {
        driver.goToUrl(url);
    }

    public void checkConditions(Account option) {
        switch (option) {
            case LOGIN -> {
                elements().termsCheckbox().click();
            }
            case REGISTER_ACCOUNT -> {
                elements().privacyPoliceCheckbox().click();
                elements().termsCheckbox().click();
            }
            case GUEST_ACCOUNT -> {
                elements().termsCheckbox().click();
            }
        }
    }

    public void continuePurchase() {
        driver.waitUntilPageLoadsCompletely();
        elements().continueButton().click();
        driver.waitForAjax();
    }

    public void fillPersonalDetails(PersonInfo personInfo) {
        elements().firstNameField().typeText(personInfo.firstName);
        elements().lastNameField().typeText(personInfo.lastName);
        elements().emailField().typeText(personInfo.email);
        elements().telephoneField().typeText(personInfo.telephone);
    }

    public void fillPasswordFields(String password, String confirmPassword) {
        elements().paymentPassword().typeText(password);
        elements().paymentConfirmPassword().typeText(confirmPassword);
    }

    public void fillPasswordFields(String password) {
        elements().paymentPassword().typeText(password);
        elements().paymentConfirmPassword().typeText(password);
    }

    public void fillAccountDetails(PersonInfo person, PaymentAddressInfo paymentAddress, Account option) {
        elements().accountOption(option).click();
        switch (option) {
            case LOGIN -> {
                fillLoginAccount(person.getEmail(), person.getPassword());
                driver.waitForAjax();
                elements().newPaymentAddress().click();
                elements().firstNameField().typeText(person.getFirstName());
                elements().lastNameField().typeText(person.getLastName());
                fillPaymentAddress(paymentAddress);
            }
            case REGISTER_ACCOUNT -> {
                fillPersonalDetails(person);
                fillPaymentAddress(paymentAddress);
                fillPasswordFields(person.password);
            }
            case GUEST_ACCOUNT -> {
                fillPersonalDetails(person);
                fillPaymentAddress(paymentAddress);
            }
        }

        checkConditions(option);
        driver.waitForAjax();
    }

    public List<Double> getPrices() {
        List<Double> prices = new ArrayList<Double>();
        prices.add(PriceConvertingExtensions.getAmount(elements().selectCheckoutTotalInfo(TablePrice.SUB_TOTAL).getText()));
        try {
            prices.add(PriceConvertingExtensions.getAmount(elements().selectCheckoutTotalInfo(TablePrice.FLAT_SHIPPING_RATE).getText()));
        } catch (Exception ex) {
            prices.add(PriceConvertingExtensions.getAmount(elements().selectCheckoutTotalInfo(TablePrice.ECO_TAX).getText()));
            prices.add(PriceConvertingExtensions.getAmount(elements().selectCheckoutTotalInfo(TablePrice.VAT).getText()));
            return prices;
        }
        prices.add(PriceConvertingExtensions.getAmount(elements().selectCheckoutTotalInfo(TablePrice.ECO_TAX).getText()));
        prices.add(PriceConvertingExtensions.getAmount(elements().selectCheckoutTotalInfo(TablePrice.VAT).getText()));

        return prices;
    }

    public List<ProductInfo> getProductsInfo() {
        var products = new ArrayList<ProductInfo>();

        for (var tableRow : elements().checkoutTableRows()) {
            var currentProductInfo = new ProductInfo();
            var currentRowCells = tableRow.findElements(By.tagName("td"));
            currentProductInfo.productLink = currentRowCells.get(0).findElement(By.tagName("a")).
                getAttribute("href");
            var imageLink = currentRowCells.get(0).findElement(By.tagName("img")).getAttribute("src");
            String imageLinkWithoutSize = imageLink.substring(0, imageLink.lastIndexOf('/'));
            currentProductInfo.image = imageLinkWithoutSize;
            String[] product = currentRowCells.get(1).getText().split("\n");
            currentProductInfo.productName = product[0];
            currentProductInfo.model = product[2].
                replace("Model: ", "");
            currentProductInfo.quantity = Integer.parseInt(currentRowCells.get(2).findElement(
                By.xpath("//*[contains(@id, 'quantity')]")).getAttribute("value"));
            currentProductInfo.unitPrice = PriceConvertingExtensions.getAmount(currentRowCells.get(3).getText());
            currentProductInfo.unitPriceCurrency = PriceConvertingExtensions.getCurrencySign(currentRowCells.get(4).
                getText());
            currentProductInfo.total = PriceConvertingExtensions.getAmount(currentRowCells.get(4).getText());
            currentProductInfo.totalCurrency = PriceConvertingExtensions.getCurrencySign(currentRowCells.get(4).getText());

            products.add(currentProductInfo);
        }

        return products;
    }

    public void updateProductQuantity(int newQuantity, Item product) {
        elements().quentityField(product).typeText(String.valueOf(newQuantity));
        driver.waitUntilPageLoadsCompletely();
    }

    public double totalPriceWithTaxes() {
        double totalSum = 0;
        var prices = getPrices();
        for (int i = 0; i < prices.size(); i++) {
            totalSum += prices.get(i);
        }

        return totalSum;
    }

    public double estimateEcoTax() {
        var ecoTax = countOfProducts() * getEco() + getEco();

        return ecoTax;
    }

    private void fillLoginAccount(String email, String password) {
        elements().loginEmail().typeText(email);
        elements().loginPassword().typeText(password);
        elements().loginButton().click();
    }

    private double getVat() {
        double temp = PriceConvertingExtensions.getAmount(elements().getVatTax().getText());

        return temp;
    }

    private double getEco() {
        double temp = PriceConvertingExtensions.getAmount(elements().getEcoTax().getText());

        return temp;
    }

    public double estimateVAT() {
        var flatShippingRate = PriceConvertingExtensions.getAmount(
            elements().selectCheckoutTotalInfo(TablePrice.FLAT_SHIPPING_RATE).getText());
        var subTotal = PriceConvertingExtensions.getAmount(
            elements().selectCheckoutTotalInfo(TablePrice.SUB_TOTAL).getText());
        var vat = ((subTotal + flatShippingRate) * getVat()) / 100;

        return vat;
    }

    private int countOfProducts() {
        var count = 0;

        for (var item : productsQuantityCheckout()) {
            count += item;
        }

        return count;
    }

    private List<Integer> productsQuantityCheckout() {
        var list = new ArrayList<Integer>();

        for (var item : elements().tableProductQuantity()) {

            for (var cell : item.findElements(By.tagName("input"))) {
                list.add(Integer.parseInt(cell.getAttribute("value")));
            }
        }

        return list;
    }
}