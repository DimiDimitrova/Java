package org.example.editaccountpage;

import org.example.core.BaseEShopPage;
import org.example.core.Driver;
import org.example.models.PersonInfo;

public class EditAccountPage extends BaseEShopPage {
    public EditAccountPage(Driver driver) {
        super(driver);
    }

    public EditAccountElements elements() {
        return new EditAccountElements(driver);
    }

    public void fillAllAccountInformation(PersonInfo person) {
        elements().firstNameInput().typeText(person.getFirstName());
        elements().lastNameInput().typeText(person.getLastName());
        elements().emailInput().typeText(person.getEmail());
        elements().telephoneInput().typeText(person.getTelephone());
    }
}