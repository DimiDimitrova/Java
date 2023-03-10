package org.example.enums;

public enum MainHeader {
    CART("cart"),
    COMPARE("compare"),
    WISHLIST("wishlist");
    private final String name;

    MainHeader(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}