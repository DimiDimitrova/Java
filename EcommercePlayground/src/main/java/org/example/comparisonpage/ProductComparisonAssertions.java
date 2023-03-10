package org.example.comparisonpage;

import org.example.ApplicationMessages;
import org.example.core.Driver;
import org.junit.jupiter.api.Assertions;

public class ProductComparisonAssertions {
    private Driver driver;

    public ProductComparisonAssertions(Driver driver) {
        this.driver = driver;
    }

    private ProductComparisonElements productComparisonElements() {
        return new ProductComparisonElements(driver);
    }

    private ProductComparison productComparison() {
        return new ProductComparison(driver);
    }


    public void assertProductIsRemoved() {
        Assertions.assertTrue(productComparisonElements().emptyCompareContext().isDisplayed(),
            String.format(ApplicationMessages.NOT_EXISTS_IN_PAGE_ERROR,
                productComparisonElements().emptyCompareContext().getText()));

        Assertions.assertTrue(productComparisonElements().modifiedMessage().isDisplayed(),
            String.format(ApplicationMessages.NOT_EXISTS_IN_PAGE_ERROR,
                productComparisonElements().modifiedMessage().getText()));
    }
}
