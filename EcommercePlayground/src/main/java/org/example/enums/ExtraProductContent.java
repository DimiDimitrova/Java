package org.example.enums;

public enum ExtraProductContent {
    PRODUCT_CODE("Product Code"),
    VIEWED("Viewed"),
    AVAILABILITY("Availability"),
    REWARD_POINTS("Reward Points");
    private final String name;

    ExtraProductContent(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}