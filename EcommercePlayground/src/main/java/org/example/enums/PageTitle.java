package org.example.enums;

public enum PageTitle {
    ACCOUNT("Account"),
    REGISTER("Register"),
    PRODUCT("Product"),
    LOGIN("Login"),
    HOME("Home"),
    CHECKOUT("Checkout"),
    CART("Cart"),
    CONFIRM("Confirm"),
    SPECIAL_OFFERS("Special Offers");
    private final String name;

    PageTitle(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
