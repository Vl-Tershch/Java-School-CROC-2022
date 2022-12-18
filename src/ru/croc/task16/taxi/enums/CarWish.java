package ru.croc.task16.taxi.enums;

import java.util.HashMap;
import java.util.Map;

// Enum of type wishes
public enum CarWish {
    WATER ("Вода"),
    CHARGING ("Зарядные устройства"),
    CHILD_SEAT ("Детское кресло"),
    MUSIC ("Музыка");

    private final String title;
    private static final Map<String, CarWish> lookup = new HashMap<>();

    static {
        for (CarWish d : CarWish.values()) {
            lookup.put(d.getTitle(), d);
        }
    }

    CarWish(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static CarWish get(String abbreviation) {
        return lookup.get(abbreviation);
    }

    @Override
    public String toString() {
        return "CarWish{" +
                "title='" + title + '\'' +
                '}';
    }
}
