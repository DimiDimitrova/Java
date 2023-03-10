package org.example.accountpage;

import org.example.core.BaseEShopPage;
import org.example.core.Driver;
import org.example.enums.Navbar;

public class AccountPage extends BaseEShopPage {
    public AccountPage(Driver driver) {
        super(driver);
    }

    public AccountElements elements() {
        return new AccountElements(driver);
    }

    public AccountAssertions assertions() {
        return new AccountAssertions(driver);
    }

    public void openMenuFromNavbar(Navbar menu) {
        elements().accountNavbar(menu).click();
    }
}