package org.example.enums;

public enum GiftCertificate {
    GENERAL("General"),
    BIRTHDAY("Birthday"),
    CHRISTMAS("Christmas");
    private final String name;

    GiftCertificate(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}