package org.example.enums;

public enum CategoryInSearchBox {
    ALL_CATEGORIES(0),
    DESKTOP(20),
    LAPTOPS(18),
    COMPONENTS(25),
    TABLETS(57),
    SOFTWARE(17),
    PHONES_PD_AS(24),
    CAMERAS(33),
    MP_3_PLAYERS(34);
    private final int id;

    CategoryInSearchBox(int id) {
        this.id = id;
    }
    public int categoryId(){
        return id;
    }
}
