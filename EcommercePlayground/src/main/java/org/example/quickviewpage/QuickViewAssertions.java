package org.example.quickviewpage;

import org.example.ApplicationMessages;
import org.example.core.Driver;
import org.junit.jupiter.api.Assertions;

public class QuickViewAssertions {
    private Driver driver;

    public QuickViewAssertions(Driver driver) {
        this.driver = driver;
    }

    private QuickViewElements quickViewElements() {
        return new QuickViewElements(driver);

    }

    public void assertQuantityButtonWork(int expectedQuantity) {
        Assertions.assertEquals(String.valueOf(expectedQuantity),
            quickViewElements().quickViewQuantityInput().getAttribute("value"),
            String.format(ApplicationMessages.NOT_EQUAL_ERROR, String.valueOf(expectedQuantity),
                quickViewElements().quickViewQuantityInput().getAttribute("value")));
    }
}