package org.example.changepasswordpage;

import org.example.core.BaseEShopPage;
import org.example.core.Driver;

public class ChangePasswordPage extends BaseEShopPage {

    public ChangePasswordPage(Driver driver) {
        super(driver);
    }

    public ChangePasswordElements elements() {
        return new ChangePasswordElements(driver);
    }

    public ChangePasswordAssertions assertions(){
        return new ChangePasswordAssertions(driver);
    }

    public void changePassword(String newPassword) {
        elements().passwordInput().typeText(newPassword);
        elements().confirmPasswordInput().typeText(newPassword);
    }
}