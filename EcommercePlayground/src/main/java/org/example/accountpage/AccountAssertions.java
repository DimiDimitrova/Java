package org.example.accountpage;

import org.example.ApplicationMessages;
import org.example.core.Driver;
import org.example.enums.Navbar;
import org.junit.jupiter.api.Assertions;

public class AccountAssertions {
    private Driver driver;

    public AccountAssertions(Driver driver) {
        this.driver = driver;
    }

    private AccountElements accountPageElements() {
        return new AccountElements(driver);
    }

    public void assertThatUserIsLogged() {
        Assertions.assertTrue(accountPageElements().accountNavbar(Navbar.LOGOUT).isDisplayed(),
            String.format(ApplicationMessages.NOT_EXISTS_IN_PAGE_ERROR, accountPageElements().accountNavbar(Navbar.LOGOUT).getText()));
    }

    public void assertUserIsNotLogged() {
        Assertions.assertTrue(accountPageElements().accountNavbar(Navbar.LOGIN).isDisplayed(),
            ApplicationMessages.LOGGED_MESSAGE);
    }
}