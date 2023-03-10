package org.example.enums;

public enum TablePrice {
    SUB_TOTAL("Sub-Total:"),
    FLAT_SHIPPING_RATE("Flat Shipping Rate:"),
    ECO_TAX("Eco Tax"),
    VAT("VAT"),
    TOTAL("Total:");
    private final String name;

    TablePrice(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}