package org.example.accountorderpage;

import org.example.core.BaseEShopPage;
import org.example.core.Driver;

public class AccountOrderPage extends BaseEShopPage {
    public AccountOrderPage(Driver driver) {
        super(driver);
    }

    public AccountOrderElements elements() {
        return new AccountOrderElements(driver);
    }

    public AccountOrderAssertions assertions() {
        return new AccountOrderAssertions(driver);
    }
}