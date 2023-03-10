package org.example.enums;

public enum MegaMenu {
    MOBILES("Mobiles"),
    LAPTOPS("Laptops"),
    ACCESSORIES("Accessories"),
    COMPUTERS("Computers"),
    TABLETS("Tablets"),
    SOUND_SYSTEM("Sound System"),
    SMART_WEARABLE("Smart Wearable");
    private final String name;

    MegaMenu(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}