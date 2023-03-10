package org.example.enums;

public enum Navbar {

    MY_ACCOUNT("account"),
    EDIT_ACCOUNT("edit"),
    PASSWORD("password"),
    ADDRESS_BOOK("address"),
    WISH_LIST("wishlist"),
    NOTIFICATION("notification"),
    ORDER_HISTORY("order"),
    DOWNLOADS("download"),
    RECURRING_PAYMENTS("recurring"),
    REWARD_POINTS("reward"),
    RETURNS("returns"),
    TRANSACTION("transaction"),
    NEWSLETTER("newsletter"),
    LOGOUT("logout"),
    LOGIN("login"),
    REGISTER("register");

    private final String name;

    Navbar(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}