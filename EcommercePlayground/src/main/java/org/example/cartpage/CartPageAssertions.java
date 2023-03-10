package org.example.cartpage;

import org.example.ApplicationMessages;
import org.example.core.Driver;
import org.example.enums.PageTitle;
import org.example.enums.TablePrice;
import org.example.extensions.PriceConvertingExtensions;
import org.example.models.Product;
import org.example.sections.breadcrumbsection.BreadcrumbSection;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class CartPageAssertions {
    private Driver driver;

    public CartPageAssertions(Driver driver) {
        this.driver = driver;
    }

    private CartPageElements cartPageElements() {
        return new CartPageElements(driver);
    }

    private CartPage cartPage() {
        return new CartPage(driver);
    }

    private BreadcrumbSection breadcrumbSection() {
        return new BreadcrumbSection(driver);
    }

    public void assertProductInfo(List<Product> expectedProductInfo) {
        var actualProductsInfo = cartPage().shoppingTableContent();

        if (actualProductsInfo.size() != expectedProductInfo.size()) {
            throw new IllegalArgumentException("The actual items are different than the expected ones.");
        }

        for (int i = 0; i < actualProductsInfo.size(); i++) {
            Assertions.assertEquals(expectedProductInfo.get(i).title, actualProductsInfo.get(i).productName,
                String.format(
                    ApplicationMessages.NOT_EXISTS_IN_PAGE_ERROR, expectedProductInfo.get(i).title));

            Assertions.assertEquals(expectedProductInfo.get(i).image, actualProductsInfo.get(i).image,
                String.format(
                    ApplicationMessages.NOT_EXISTS_IN_PAGE_ERROR, expectedProductInfo.get(i).image));

            Assertions.assertEquals(expectedProductInfo.get(i).unitPrice, actualProductsInfo.get(i).unitPrice,
                String.format(
                    ApplicationMessages.NOT_EXISTS_IN_PAGE_ERROR, expectedProductInfo.get(i).unitPrice));

            Assertions.assertEquals(expectedProductInfo.get(i).model, actualProductsInfo.get(i).model,
                String.format(
                    ApplicationMessages.NOT_EXISTS_IN_PAGE_ERROR, expectedProductInfo.get(i).model));

            Assertions.assertEquals(expectedProductInfo.get(i).unitPrice * actualProductsInfo.get(i).quantity,
                actualProductsInfo.get(i).total,
                String.format(ApplicationMessages.NOT_EXISTS_IN_PAGE_ERROR,
                    expectedProductInfo.get(i).unitPrice * actualProductsInfo.get(i).quantity));
        }
    }

    public void assertCartPageIsOpen() {
        breadcrumbSection().assertions().assertMenuIsOpen("Shopping Cart");
        Assertions.assertTrue(driver.getCurrentUrl().endsWith("cart"),
            String.format(ApplicationMessages.PAGE_ERROR, PageTitle.CART.toString()));
    }

    public void assertQuantityAndTotalAreCorrect(int expectedQty, Product product) {
        Assertions.assertEquals(cartPageElements().quantityField(product.getTitle()).getAttribute("value"),
            Integer.toString(expectedQty),
            String.format(ApplicationMessages.QTY_ERROR,
                cartPageElements().quantityField(product.getTitle()).getText(), Integer.toString(expectedQty)));

        var unitPriceBeforeUpdate = PriceConvertingExtensions.getAmount(cartPageElements().unitPrice(product.getTitle()).getText());
        var unitPrice = unitPriceBeforeUpdate * expectedQty;
        var totalProductSum = PriceConvertingExtensions.getAmount(cartPageElements().total(product.getTitle()).getText());

        Assertions.assertEquals(totalProductSum, unitPrice,
            String.format(ApplicationMessages.NOT_EQUAL_ERROR, totalProductSum, unitPrice));
    }

    public void assertMessageForEmptyCartIsDisplayed() {
        Assertions.assertTrue(cartPageElements().shoppingCartIsEmptyMessage().isDisplayed(),
            ApplicationMessages.NOT_EMPTY_CART_MESSAGE);
    }

    public void assertFlatRateIsApplied(String appliedRate) {
        var rate = PriceConvertingExtensions.getAmount(appliedRate.substring(21, appliedRate.length()));
        var prices = cartPage().getPrices();

        Assertions.assertEquals(prices.get(1), rate,
            String.format(ApplicationMessages.NOT_EQUAL_ERROR, rate, prices.get(1)));
    }

    public void assertEstimateShippingTaxesErrorsPresent() {
        Assertions.assertTrue(cartPageElements().regionInvalidMessage().isDisplayed(),
            String.format(ApplicationMessages.NOT_EXISTS_IN_PAGE_ERROR, cartPageElements().regionInvalidMessage().getText()));

        Assertions.assertTrue(cartPageElements().postCodeInvalidMessage().isDisplayed(),
            String.format(ApplicationMessages.NOT_EXISTS_IN_PAGE_ERROR, cartPageElements().postCodeInvalidMessage().getText()));
    }

    public void assertGiftCertificateIsApplied() {
        Assertions.assertFalse(cartPageElements().giftError().isDisplayed(),
            ApplicationMessages.GIFT_CERTIFICATE_ERROR);
    }

    public void assertTotalPriceIsCorrect() {
        var total = PriceConvertingExtensions.getAmount(cartPageElements().selectCartPriceInformation(TablePrice.TOTAL).getText());
        Assertions.assertEquals(total, cartPage().totalPriceProducts(),
            String.format(ApplicationMessages.NOT_EQUAL_ERROR, total, cartPage().totalPriceProducts()));
    }

    public void assertEcoTaxIsCorrect() {
        var ecoTax = PriceConvertingExtensions.getAmount(cartPageElements().selectCartPriceInformation(TablePrice.ECO_TAX).getText());
        var estimatedEcoTax = cartPage().estimateEcoTax();

        Assertions.assertEquals((estimatedEcoTax), ecoTax,
            String.format(ApplicationMessages.NOT_EQUAL_ERROR, ecoTax, estimatedEcoTax));
    }

    public void assertVatIsCorrect() {
        var totalVat = PriceConvertingExtensions.getAmount(cartPageElements().selectCartPriceInformation(TablePrice.VAT).getText());
        var estimatedVat = cartPage().estimateVAT();
        Assertions.assertEquals(totalVat, estimatedVat,
            String.format(ApplicationMessages.NOT_EQUAL_ERROR, totalVat, estimatedVat));
    }

    public void assertShoppingCartIsEmpty() {
        driver.waitForAjax();

        Assertions.assertTrue(driver.getCurrentUrl().endsWith("checkout/cart"),
            String.format(ApplicationMessages.PAGE_ERROR, PageTitle.CART.toString()));

        Assertions.assertTrue(cartPageElements().shoppingCartIsEmptyMessage().isDisplayed(),
            String.format(ApplicationMessages.NOT_EXISTS_IN_PAGE_ERROR, cartPageElements().shoppingCartIsEmptyMessage().getText()));
    }
}