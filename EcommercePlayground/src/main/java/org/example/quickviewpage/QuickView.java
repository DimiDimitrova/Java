package org.example.quickviewpage;

import org.example.core.BaseEShopPage;
import org.example.core.Driver;

public class QuickView extends BaseEShopPage {
    public QuickView(Driver driver) {
        super(driver);
    }

    public QuickViewElements elements() {
        return new QuickViewElements(driver);
    }

    public QuickViewAssertions assertions() {
        return new QuickViewAssertions(driver);
    }
}