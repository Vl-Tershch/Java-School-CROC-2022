package ru.croc.task16.taxi.enums;

import java.util.HashMap;
import java.util.Map;

// Enum of car types
public enum CarClass {
    ECONOMY ("Эконом"),
    COMFORT ("Комфорт"),
    COMFORT_PLUS ("Комфорт +"),
    BUSINESS ("Бизнес");

    private final String title;
    private static final Map<String, CarClass> lookup = new HashMap<>();

    static {
        for (CarClass d : CarClass.values()) {
            lookup.put(d.getTitle(), d);
        }
    }

    CarClass(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static CarClass get(String abbreviation) {
        return lookup.get(abbreviation);
    }

    @Override
    public String toString() {
        return "CarClass{" +
                "title='" + title + '\'' +
                '}';
    }
}
