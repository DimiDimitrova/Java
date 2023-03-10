package org.example.loginpage;

import org.example.core.BaseEShopPage;
import org.example.core.Driver;
import org.example.enums.MainMenu;
import org.example.enums.MyAccountDropDown;

public class LoginPage extends BaseEShopPage {
    public LoginPage(Driver driver) {
        super(driver);
    }

    public LoginElements elements() {
        return new LoginElements(driver);
    }

    public LoginAssertions assertions() {
        return new LoginAssertions(driver);
    }

    protected String url = "https://ecommerce-playground.lambdatest.io";

    public void logIn(String email, String password) {
        driver.goToUrl(url);
        mainNavigationSections().moveToMainMenu(MainMenu.MY_ACCOUNT);

        myAccountDropDownSections().elements().myAccountMenu(MyAccountDropDown.LOGIN).click();
        driver.waitUntilPageLoadsCompletely();
        elements().emailInput().typeText(email);
        elements().passwordInput().typeText(password);
        elements().loginButton().click();
        driver.waitForAjax();
    }
}