package org.example.enums;

public enum Item {
    IPOD_TOUCH("iPod Touch", 32),
    IPOD_SHUFFLE("iPod Shuffle", 34),
    IPOD_NANO("iPod Nano", 36),
    IPOD_CLASSIC("iPod Classic", 48),
    IPHONE("iPhone", 40),
    IMAC("iMac", 41),
    APPLE_CINEMA_30("Apple Cinema 30 \"", 42),
    MAC_BOOK("MacBook", 43),
    MAC_BOOK_PRO("MacBook Pro", 45),
    MAC_BOOK_AIR("MacBook Air", 44),
    HTC_TOUCH_HD("HTC Touch HD", 28),
    PALM_TREO_PRO("Palm Treo Pro", 29),
    NIKON_D_300("Nikon D300", 31),
    CANON_EOS_5_D("Canon EOS 5D", 30),
    SAMSUNG_SYNC_MASTER_941_BW("Samsung Sync Master 941 BW", 33),
    SONY_VAIO("Sony VAIO", 46),
    HP_LP_3065("HP LP3065", 47);
    private final String name;
    private final int code;

    Item(String name, int code) {
        this.name = name;
        this.code = code;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getId(){
        return code;
    }
}
