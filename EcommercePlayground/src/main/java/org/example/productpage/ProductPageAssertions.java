package org.example.productpage;

import org.example.ApplicationMessages;
import org.example.core.Driver;
import org.example.enums.PageTitle;
import org.junit.jupiter.api.Assertions;
import org.example.enums.ExtraProductContent;

public class ProductPageAssertions{
    private Driver driver;

    public ProductPageAssertions(Driver driver) {
        this.driver = driver;
    }

    protected ProductPageElements elements() {
        return new ProductPageElements(driver);
    }

    public void assertProductPageIsOpen() {
        Assertions.assertFalse(driver.getCurrentUrl().endsWith("#"), ApplicationMessages.REFRESH_PAGE_ERROR);

        Assertions.assertTrue(driver.getCurrentUrl().endsWith("product"),
            String.format(ApplicationMessages.PAGE_ERROR, PageTitle.PRODUCT.toString()));
    }

    public void assertProductInformationIsDisplayed() {
        Assertions.assertTrue(elements().product().isDisplayed(),
            String.format(ApplicationMessages.NOT_EXISTS_IN_PAGE_ERROR, elements().product().getText()));

        Assertions.assertTrue(elements().productPrice().isDisplayed(),
            String.format(ApplicationMessages.NOT_EXISTS_IN_PAGE_ERROR, elements().productPrice().getText()));

        Assertions.assertTrue(elements().productExtraContent(ExtraProductContent.AVAILABILITY).isDisplayed());

        Assertions.assertTrue(elements().productExtraContent(ExtraProductContent.PRODUCT_CODE).isDisplayed());
    }
}