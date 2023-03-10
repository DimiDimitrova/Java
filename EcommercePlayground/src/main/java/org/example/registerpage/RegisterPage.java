package org.example.registerpage;

import org.example.core.BaseEShopPage;
import org.example.core.Driver;
import org.example.enums.MainMenu;
import org.example.enums.MyAccountDropDown;
import org.example.models.PersonInfo;

public class RegisterPage extends BaseEShopPage {
    public RegisterPage(Driver driver) {
        super(driver);
    }

    public RegisterElements elements() {
        return new RegisterElements(driver);
    }

    public RegisterAssertions assertions() {
        return new RegisterAssertions(driver);
    }

    public String url = "https://ecommerce-playground.lambdatest.io";

    public String registerUrl = "https://ecommerce-playground.lambdatest.io/index.php?route=account/register";

    public void fillRegistrationForm(String email, String confirmPassword, PersonInfo personInfo) {
        elements().firstNameInput().typeText(personInfo.getFirstName());
        elements().lastNameInput().typeText(personInfo.getLastName());
        elements().emailInput().typeText(email);
        elements().telephoneInput().typeText(personInfo.getTelephone());
        elements().passwordInput().typeText(personInfo.getPassword());
        elements().confirmPassword().typeText(confirmPassword);
    }

    public void fillRegistrationForm(PersonInfo personInfo) {
        elements().firstNameInput().typeText(personInfo.getFirstName());
        elements().lastNameInput().typeText(personInfo.getLastName());
        elements().emailInput().typeText(personInfo.getEmail());
        elements().telephoneInput().typeText(personInfo.getTelephone());
        elements().passwordInput().typeText(personInfo.getPassword());
        elements().confirmPassword().typeText(personInfo.getPassword());
    }

    public void doRegistration(String email, String confirmPassword, PersonInfo person) {
        driver.goToUrl(url);
        mainNavigationSections().moveToMainMenu(MainMenu.MY_ACCOUNT);
        driver.waitUntilPageLoadsCompletely();
        myAccountDropDownSections().elements().myAccountMenu(MyAccountDropDown.REGISTER).click();
        driver.waitForAjax();
        fillRegistrationForm(email, confirmPassword, person);
        elements().agreeCheckbox().click();
        elements().continueButton().click();
        driver.waitForAjax();
    }
}