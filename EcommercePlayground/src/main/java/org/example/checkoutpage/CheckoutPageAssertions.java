package org.example.checkoutpage;

import org.example.ApplicationMessages;
import org.example.core.Driver;
import org.example.enums.Item;
import org.example.enums.PageTitle;
import org.example.enums.TablePrice;
import org.example.extensions.PriceConvertingExtensions;
import org.example.models.Product;
import org.example.sections.breadcrumbsection.BreadcrumbSection;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static org.example.checkoutpage.CheckoutPage.totalPrice;

public class CheckoutPageAssertions {
    private Driver driver;

    private final int PASSWORD_MIN_SIZE = 4;
    private final int PASSWORD_MAX_SIZE = 20;
    private final int FIRST_NAME_MIN_SIZE = 1;
    private final int FIRST_NAME_MAX_SIZE = 32;
    private final int LAST_NAME_MIN_SIZE = 1;
    private final int LAST_NAME_MAX_SIZE = 32;
    private final int TELEPHONE_MIN_SIZE = 3;
    private final int TELEPHONE_MAX_SIZE = 32;
    private final int ADDRESS_MIN_SIZE = 3;
    private final int ADDRESS_MAX_SIZE = 128;
    private final int CITY_MIN_SIZE = 2;
    private final int CITY_MAX_SIZE = 128;
    private final int POST_CODE_MIN_SIZE = 2;
    private final int POST_CODE_MAX_SIZE = 10;

    public CheckoutPageAssertions(Driver driver) {
        this.driver = driver;
    }

    private CheckoutPageElements checkoutPageElements() {
        return new CheckoutPageElements(driver);
    }

    private CheckoutPage checkoutPage() {
        return new CheckoutPage(driver);
    }

    private BreadcrumbSection breadcrumbSection() {
        return new BreadcrumbSection(driver);
    }

    public void checkoutPageIsLoaded() {
        Assertions.assertTrue(driver.getCurrentUrl().endsWith("checkout/checkout"),
            String.format(ApplicationMessages.PAGE_ERROR, PageTitle.CHECKOUT.toString()));
    }

    public void assertProductsInfo(List<Product> expectedProductInfo) {
        var actualProductsInfo = checkoutPage().getProductsInfo();

        if (actualProductsInfo.size() != expectedProductInfo.size()) {
            throw new IllegalArgumentException("The actual items are different than the expected ones.");
        }

        for (int i = 0; i < actualProductsInfo.size(); i++) {
            Assertions.assertEquals(actualProductsInfo.get(i).getProductName(), expectedProductInfo.get(i).getTitle(),
                String.format(ApplicationMessages.NOT_EXISTS_IN_PAGE_ERROR, expectedProductInfo.get(i).getTitle()));

            Assertions.assertEquals(actualProductsInfo.get(i).getImage(), expectedProductInfo.get(i).getImage(),
                String.format(ApplicationMessages.NOT_EXISTS_IN_PAGE_ERROR, expectedProductInfo.get(i).getImage()));

            Assertions.assertEquals(actualProductsInfo.get(i).getUnitPrice(), expectedProductInfo.get(i).getUnitPrice(),
                String.format(ApplicationMessages.NOT_EXISTS_IN_PAGE_ERROR, expectedProductInfo.get(i).getUnitPrice()));

            Assertions.assertEquals(actualProductsInfo.get(i).getModel(), expectedProductInfo.get(i).getModel(),
                String.format(ApplicationMessages.NOT_EXISTS_IN_PAGE_ERROR, expectedProductInfo.get(i).getModel()));

            Assertions.assertEquals(actualProductsInfo.get(i).getTotal(),
                expectedProductInfo.get(i).getUnitPrice() * actualProductsInfo.get(i).getQuantity(),
                String.format(ApplicationMessages.NOT_EXISTS_IN_PAGE_ERROR,
                    expectedProductInfo.get(i).getUnitPrice() * actualProductsInfo.get(i).getQuantity()));
        }
    }

    public void assertQuantityAndTotalAreCorrect(int expectedQty, Item product) {
        Assertions.assertTrue(checkoutPageElements().quantityField(product).getAttribute("value").
                equals(String.valueOf(expectedQty)),
            String.format(ApplicationMessages.QTY_ERROR,
                checkoutPageElements().quantityField(product).getText(), String.valueOf(expectedQty)));

        var unitPriceBeforeUpdate = PriceConvertingExtensions.getAmount(checkoutPageElements().unitPrice(product).getText());
        var unitPrice = unitPriceBeforeUpdate * expectedQty;
        driver.waitForAjax();
        var totalProductSum = PriceConvertingExtensions.getAmount(checkoutPageElements().totalOnProduct(product).getText());

        Assertions.assertEquals(totalProductSum, unitPrice,
            String.format(ApplicationMessages.NOT_EQUAL_ERROR, totalProductSum, unitPrice));
    }

    public void assertTotalPriceIsCorrect() {
        var total = PriceConvertingExtensions.getAmount(checkoutPageElements().total().getText());
        totalPrice = total;

        Assertions.assertEquals(totalPrice, checkoutPage().totalPriceWithTaxes(),
            String.format(ApplicationMessages.NOT_EQUAL_ERROR, totalPrice, checkoutPage().totalPriceWithTaxes()));
    }

    public void assertIncorrectFirstNameValidation() {
        Assertions.assertTrue(breadcrumbSection().elements().activePageTitle().getText().equals(PageTitle.CHECKOUT.toString()),
            ApplicationMessages.ORDER_IS_MADE + " " +
                String.format(ApplicationMessages.FIRST_NAME_ERROR, FIRST_NAME_MIN_SIZE, FIRST_NAME_MAX_SIZE));

        Assertions.assertEquals(checkoutPageElements().firstNameError().getText(),
            String.format(ApplicationMessages.FIRST_NAME_ERROR, FIRST_NAME_MIN_SIZE, FIRST_NAME_MAX_SIZE));
    }

    public void assertIncorrectLastNameValidation() {
        Assertions.assertTrue(breadcrumbSection().elements().activePageTitle().getText().equals(PageTitle.CHECKOUT.toString()),
            ApplicationMessages.ORDER_IS_MADE);

        var expectedLastNameValidationMessage = String.format(ApplicationMessages.LAST_NAME_ERROR,
            LAST_NAME_MIN_SIZE, LAST_NAME_MAX_SIZE);
        Assertions.assertEquals(checkoutPageElements().lastNameError().getText(), expectedLastNameValidationMessage);
    }

    public void assertIncorrectPostCodeValidation() {
        Assertions.assertTrue(breadcrumbSection().elements().activePageTitle().getText().equals(PageTitle.CHECKOUT.toString()),
            ApplicationMessages.ORDER_IS_MADE);

        Assertions.assertEquals(checkoutPageElements().postCodeError().getText(),
            String.format(ApplicationMessages.POSTCODE_ERROR, POST_CODE_MIN_SIZE, POST_CODE_MAX_SIZE));
    }

    public void assertIncorrectAddressValidation() {
        Assertions.assertTrue(breadcrumbSection().elements().activePageTitle().getText().equals(PageTitle.CHECKOUT.toString()),
            ApplicationMessages.ORDER_IS_MADE);

        Assertions.assertEquals(checkoutPageElements().addressError().getText(),
            String.format(ApplicationMessages.ADDRESS_ERROR, ADDRESS_MIN_SIZE, ADDRESS_MAX_SIZE));
    }

    public void assertIncorrectCityValidation() {
        Assertions.assertTrue(breadcrumbSection().elements().activePageTitle().getText().equals(PageTitle.CHECKOUT.toString()),
            ApplicationMessages.ORDER_IS_MADE);

        Assertions.assertEquals(checkoutPageElements().cityError().getText(),
            String.format(ApplicationMessages.CITY_ERROR, CITY_MIN_SIZE, CITY_MAX_SIZE));
    }

    public void assertPasswordsMismatchValidation() {
        Assertions.assertEquals(breadcrumbSection().elements().activePageTitle().getText(), PageTitle.CHECKOUT.toString(),
            ApplicationMessages.PASSWORD_CONFIRMATION_ERROR);
    }

    public void assertEmptyConfirmPasswordValidation() {
        Assertions.assertEquals(breadcrumbSection().elements().activePageTitle().getText(), PageTitle.CHECKOUT.toString(),
            ApplicationMessages.ORDER_IS_MADE);

        Assertions.assertEquals(checkoutPageElements().passwordConfirmationError().getText(),
            String.format(ApplicationMessages.PASSWORD_CONFIRMATION_ERROR));
    }

    public void assertOrderFailedWithExistEmail() {
        Assertions.assertEquals(breadcrumbSection().elements().activePageTitle().getText(), PageTitle.CHECKOUT.toString(),
            ApplicationMessages.ORDER_IS_MADE);

        Assertions.assertTrue(checkoutPageElements().emailError().isDisplayed());
    }

    public void assertIncorrectPhoneValidation() {
        Assertions.assertEquals(breadcrumbSection().elements().activePageTitle().getText(), PageTitle.CHECKOUT.toString(),
            ApplicationMessages.ORDER_IS_MADE);

        Assertions.assertEquals(checkoutPageElements().telephoneError().getText(),
            String.format(ApplicationMessages.TELEPHONE_ERROR, TELEPHONE_MIN_SIZE, TELEPHONE_MAX_SIZE));
    }

    public void assertIncorrectPasswordValidation() {
        Assertions.assertEquals(breadcrumbSection().elements().activePageTitle().getText(), PageTitle.CHECKOUT.toString(),
            ApplicationMessages.ORDER_IS_MADE);

        Assertions.assertEquals(checkoutPageElements().passwordError().getText(),
            String.format(ApplicationMessages.PASSWORD_ERROR, PASSWORD_MIN_SIZE, PASSWORD_MAX_SIZE));
    }

    public void assertIncorrectCountryValidation() {
        Assertions.assertEquals(breadcrumbSection().elements().activePageTitle().getText(), PageTitle.CHECKOUT.toString(),
            ApplicationMessages.ORDER_IS_MADE);

        Assertions.assertEquals(checkoutPageElements().countryError().getText(), ApplicationMessages.COUNTRY_ERROR);
    }

    public void assertTermConditionsRequiredAgreementValidation() {
        Assertions.assertEquals(breadcrumbSection().elements().activePageTitle().getText(), PageTitle.CHECKOUT.toString(),
            ApplicationMessages.ORDER_IS_MADE);

        Assertions.assertTrue(checkoutPageElements().termsWarningMessage().isDisplayed());
    }

    public void assertEcoTaxIsCorrect() {
        var ecoTax = PriceConvertingExtensions.getAmount(checkoutPageElements().selectCheckoutTotalInfo(TablePrice.ECO_TAX).getText());
        var estimatedEcoTax = checkoutPage().estimateEcoTax();

        Assertions.assertEquals(estimatedEcoTax, ecoTax,
            String.format(ApplicationMessages.NOT_EQUAL_ERROR, ecoTax, estimatedEcoTax));
    }

    public void assertVatIsCorrect() {
        var totalVat = PriceConvertingExtensions.getAmount(checkoutPageElements().selectCheckoutTotalInfo(TablePrice.VAT).getText());
        var estimatedVat = checkoutPage().estimateVAT();

        Assertions.assertEquals(totalVat, estimatedVat,
            String.format(ApplicationMessages.NOT_EQUAL_ERROR, totalVat, estimatedVat));
    }
}