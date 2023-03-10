package org.example.enums;

public enum Categories {
    COMPONENTS("Components"),
    CAMERAS("Cameras"),
    PHONE_TABLETS_IPOD("Phone, Tablets & Ipod"),
    SOFTWARE("Software"),
    MP_3_PLAYERS("MP3 Players"),
    LAPTOPS_NOTEBOOKS("Laptops & Notebooks"),
    DESKTOPS_AND_MONITORS("Desktops and Monitors"),
    PRINTERS_SCANNERS("Printers & Scanners"),
    MICE_AND_TRACKBALLS("Mice and Trackballs"),
    FASHION_AND_ACCESSORIES("Fashion and Accessories"),
    BEAUTY_AND_SALOON("Beauty and Saloon"),
    AUTOPARTS_AND_ACCESSORIES("Autoparts and Accessories"),
    WASHING_MACHINE("Washing machine"),
    GAMING_CONSOLES("Gaming consoles"),
    AIR_CONDITIONER("Air conditioner"),
    WEB_CAMERAS("Web Cameras");
    private final String name;

    Categories(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
