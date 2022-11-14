package ru.croc.task8;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class Task8 {
    public static void main(String[] args) {

        // Initial variables and default values
        HashMap<String, Locale> allLocales = new HashMap<>() {
            {
                put("usa",Locale.US);
                put("france",Locale.FRANCE);
                put("germany",Locale.GERMANY);
                put("japan",Locale.JAPAN);
                put("italy",Locale.ITALY);
                put("korea",Locale.KOREA);
                put("china",Locale.CHINA);
                put("canada",Locale.CANADA);
                put("uk",Locale.UK);
            }
        };
        Locale locale = Locale.US;
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);

        // Reading values from the console
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a locale: ");
        String localeString = scanner.nextLine();
        System.out.print("Enter a double: ");
        String priceString = scanner.nextLine();
        try {
            if (!localeString.isEmpty()) {
                if (!allLocales.containsKey(localeString.toLowerCase())) {
                    throw new IllegalArgumentException();
                } else {
                    locale = allLocales.get(localeString.toLowerCase());
                    numberFormat = NumberFormat.getCurrencyInstance(locale);
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Incorrect input locale: " + localeString);
        }

        // Output the received value
        try {
            BigDecimal price = new BigDecimal(priceString);
            if (!allLocales.containsKey(localeString.toLowerCase())) {
                System.out.println("Price using default value: " + numberFormat.format(price));
            } else {
                System.out.println("Result: " + numberFormat.format(price));
            }
        } catch (NumberFormatException e) {
            System.out.println("Can not convert string: " + priceString + " to double!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
