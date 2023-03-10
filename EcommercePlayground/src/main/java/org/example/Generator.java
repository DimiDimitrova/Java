package org.example;

import com.github.javafaker.Faker;
import org.example.enums.Country;
import org.example.models.PaymentAddressInfo;
import org.example.models.PersonInfo;
import org.example.models.RecipientInfo;

public class Generator {
    private Faker faker;

    public Generator() {
        faker = new Faker();
    }

    public PersonInfo createPersonInfo() {
        var person = new PersonInfo();
        person.setFirstName(faker.name().firstName());
        person.setLastName(faker.name().lastName());
        person.setEmail(faker.bothify("?????????@gmail.com"));
        person.setTelephone(faker.phoneNumber().subscriberNumber(32));
        person.setPassword(faker.lorem().characters(20));

        return person;
    }

    public PersonInfo createPersonWithSpecificFirstName(int firstNameSize) {
        var person = new PersonInfo();
        person.setFirstName(faker.lorem().characters(firstNameSize));
        person.setLastName(faker.name().lastName());
        person.setEmail(faker.bothify("???????@gmail.com"));
        person.setTelephone(faker.phoneNumber().subscriberNumber(32));
        person.setPassword(faker.lorem().characters(20));

        return person;
    }

    public PersonInfo createPersonWithSpecificLastName(int lastNameSize) {
        var person = new PersonInfo();
        person.setFirstName(faker.name().firstName());
        person.setLastName(faker.lorem().characters(lastNameSize));
        person.setEmail(faker.bothify("???????@gmail.com"));
        person.setTelephone(faker.phoneNumber().subscriberNumber(32));
        person.setPassword(faker.lorem().characters(20));

        return person;
    }

    public PersonInfo createPersonWithSpecificTelephone(int telephoneSize) {
        var person = new PersonInfo();
        person.setFirstName(faker.name().firstName());
        person.setLastName(faker.name().lastName());
        person.setEmail(faker.bothify("???????@gmail.com"));
        person.setTelephone(faker.phoneNumber().subscriberNumber(telephoneSize));
        person.setPassword(faker.lorem().characters(20));

        return person;
    }

    public PersonInfo createPersonWithSpecificPassword(int passwordSize) {
        var person = new PersonInfo();
        person.setFirstName(faker.name().firstName());
        person.setLastName(faker.name().lastName());
        person.setEmail(faker.bothify("?????????@gmail.com"));
        person.setTelephone(faker.phoneNumber().subscriberNumber(32));
        person.setPassword(faker.lorem().characters(passwordSize));

        return person;
    }

    public PersonInfo getRegisteredUser() {
        var person = new PersonInfo();
        person.setFirstName("Test");
        person.setLastName("Test");
        person.setEmail("abcde@yahoo.com");
        person.setTelephone(faker.phoneNumber().subscriberNumber(32));
        person.setPassword("login");

        return person;
    }

    public RecipientInfo getRegisteredRecipients() {
        var recipient = new RecipientInfo();
        recipient.recipientEmail = "d@abv.bg";
        recipient.recipientName = createText();
        recipient.recipientPassword = "dimi";

        return recipient;
    }

    public PaymentAddressInfo createPaymentAddress() {
        var paymentAddress = new PaymentAddressInfo();
        paymentAddress.setFirstName(faker.name().firstName());
        paymentAddress.setLastName(faker.name().lastName());
        paymentAddress.setAddress(faker.address().streetAddress());
        paymentAddress.setCity(faker.address().city());
        paymentAddress.setPostCode(faker.address().zipCode());
        paymentAddress.setCountry(faker.options().option(Country.class));
        paymentAddress.setRegion("1");

        return paymentAddress;
    }

    public PaymentAddressInfo createPaymentWithSpecificAddress(int addressSize) {
        var paymentAddress = new PaymentAddressInfo();
        paymentAddress.setFirstName(faker.name().firstName());
        paymentAddress.setLastName(faker.name().lastName());
        paymentAddress.setAddress(faker.lorem().characters(addressSize));
        paymentAddress.setCity(faker.address().city());
        paymentAddress.setPostCode(faker.address().zipCode());
        paymentAddress.setCountry(faker.options().option(Country.class));
        paymentAddress.setRegion("1");

        return paymentAddress;
    }

    public PaymentAddressInfo createPaymentWithSpecificCity(int citySize) {
        var paymentAddress = new PaymentAddressInfo();
        paymentAddress.setFirstName(faker.name().firstName());
        paymentAddress.setLastName(faker.name().lastName());
        paymentAddress.setAddress(faker.address().streetAddress());
        paymentAddress.setCity(faker.lorem().characters(citySize));
        paymentAddress.setPostCode(faker.address().zipCode());
        paymentAddress.setCountry(faker.options().option(Country.class));
        paymentAddress.setRegion("1");

        return paymentAddress;
    }

    public PaymentAddressInfo createPaymentWithSpecificPostCode(int postCodeSize) {
        var paymentAddress = new PaymentAddressInfo();
        paymentAddress.setFirstName(faker.name().firstName());
        paymentAddress.setLastName(faker.name().lastName());
        paymentAddress.setAddress(faker.address().streetAddress());
        paymentAddress.setCity(faker.address().city());
        paymentAddress.setPostCode(faker.lorem().characters(postCodeSize));
        paymentAddress.setCountry(faker.options().option(Country.class));
        paymentAddress.setRegion("1");

        return paymentAddress;
    }

    public PaymentAddressInfo createPaymentWithSelectCountryOption() {
        var paymentAddress = new PaymentAddressInfo();
        paymentAddress.setFirstName(faker.name().firstName());
        paymentAddress.setLastName(faker.name().lastName());
        paymentAddress.setAddress(faker.address().streetAddress());
        paymentAddress.setCity(faker.address().city());
        paymentAddress.setPostCode(faker.address().zipCode());
        paymentAddress.setCountry(faker.options().option(Country.PLEASE_SELECT));
        paymentAddress.setRegion("1");

        return paymentAddress;
    }


    public RecipientInfo createRecipient() {
        var recepient = new RecipientInfo();
        recepient.setRecipientName(faker.name().firstName());
        recepient.setRecipientEmail(faker.bothify("?????????@gmail.com"));
        recepient.setRecipientPassword(faker.lorem().characters(32));

        return recepient;
    }

    public RecipientInfo createRecipientWithSpecificName(int nameSize) {
        var recepient = new RecipientInfo();
        recepient.setRecipientName(faker.lorem().characters(nameSize));
        recepient.setRecipientEmail(faker.bothify("?????????@gmail.com"));
        recepient.setRecipientPassword(faker.lorem().characters(32));

        return recepient;
    }

    public RecipientInfo createRecipientWithSpecificEmail(String email) {
        var recepient = new RecipientInfo();
        recepient.setRecipientName(faker.name().firstName());
        recepient.setRecipientEmail(email);
        recepient.setRecipientPassword(faker.lorem().characters(32));

        return recepient;
    }

    public String createText() {
        return faker.lorem().toString();
    }

    public String createTextWithSpecificLength(int length) {
        return faker.lorem().characters(length);
    }
}