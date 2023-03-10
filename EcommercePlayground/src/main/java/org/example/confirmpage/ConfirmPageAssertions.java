package org.example.confirmpage;

import org.example.ApplicationMessages;
import org.example.core.Driver;
import org.example.enums.Item;
import org.example.enums.PageTitle;
import org.example.extensions.PriceConvertingExtensions;
import org.example.models.PaymentAddressInfo;
import org.example.models.Product;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class ConfirmPageAssertions {
    private Driver driver;

    public ConfirmPageAssertions(Driver driver) {
        this.driver = driver;
    }

    private ConfirmPageElements confirmPageElements() {
        return new ConfirmPageElements(driver);
    }

    private ConfirmPage confirmPage() {
        return new ConfirmPage(driver);
    }

    public void assertTotalSum(double expectedTotalSum) {
        Assertions.assertEquals(PriceConvertingExtensions.getAmount(confirmPageElements().totalSum().getText()), expectedTotalSum,
            String.format(ApplicationMessages.SUM_ERROR, confirmPageElements().totalSum().getText()));
    }

    public void assertProductQuantityIsEdited(int quantity, Item product) {
        Assertions.assertEquals(quantity, Integer.parseInt(confirmPageElements().productQuantity(product).getText()),
            String.format(ApplicationMessages.NOT_EQUAL_ERROR,
                Integer.parseInt(confirmPageElements().productQuantity(product).getText(), quantity)));
    }

    public void assertPaymentInfo(PaymentAddressInfo address) {
        String[] content = confirmPageElements().paymentTable().getText().split("\r\n");
        var regionCountry = String.format("%s,%s", address.region, address.country);
        var postCodeCity = String.format("%s %s", address.city, address.postCode);

        Assertions.assertEquals(address.address, content[1],
            String.format(ApplicationMessages.PAYMENT_ERROR, address.address));

        Assertions.assertEquals(postCodeCity, content[2]);
        Assertions.assertEquals(regionCountry, content[3],
            String.format(ApplicationMessages.PAYMENT_ERROR, regionCountry));
    }

    public void assertProductContentIsCorrect(List<Product> expectedProductInfo) {
        var actualProductsInfo = confirmPage().getProductsInfo();

        if (actualProductsInfo.size() != expectedProductInfo.size()) {
            throw new IllegalArgumentException("The actual items are different than the expected ones.");
        }

        for (int i = 0; i < actualProductsInfo.size(); i++) {
            Assertions.assertEquals(actualProductsInfo.get(i).productName, expectedProductInfo.get(i).title,
                String.format(ApplicationMessages.NOT_EXISTS_IN_PAGE_ERROR, expectedProductInfo.get(i).title));


            Assertions.assertEquals(actualProductsInfo.get(i).unitPrice, expectedProductInfo.get(i).unitPrice,
                String.format(ApplicationMessages.NOT_EXISTS_IN_PAGE_ERROR, expectedProductInfo.get(i).unitPrice));

            Assertions.assertEquals(actualProductsInfo.get(i).model, expectedProductInfo.get(i).model,
                String.format(ApplicationMessages.NOT_EXISTS_IN_PAGE_ERROR, expectedProductInfo.get(i).model));

            Assertions.assertEquals(actualProductsInfo.get(i).total,
                expectedProductInfo.get(i).unitPrice * actualProductsInfo.get(i).quantity,
                String.format(ApplicationMessages.NOT_EXISTS_IN_PAGE_ERROR,
                    expectedProductInfo.get(i).unitPrice * actualProductsInfo.get(i).quantity));
        }
    }

    public void confirmPageIsLoaded() {
        Assertions.assertTrue(driver.getCurrentUrl().endsWith("checkout/confirm"),
            String.format(ApplicationMessages.PAGE_ERROR, PageTitle.CONFIRM.toString()));
    }
}