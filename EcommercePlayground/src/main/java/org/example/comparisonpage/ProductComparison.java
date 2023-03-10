package org.example.comparisonpage;

import org.example.core.BaseEShopPage;
import org.example.core.Driver;

public class ProductComparison extends BaseEShopPage {
    public ProductComparison(Driver driver) {
        super(driver);
    }

    public ProductComparisonElements elements() {
        return new ProductComparisonElements(driver);
    }

    public ProductComparisonAssertions assertions() {
        return new ProductComparisonAssertions(driver);
    }
}