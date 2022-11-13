package ru.croc.task8;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task8 {
    public static void main(String[] args) {

        // Initial variables and default values
        Map<String, Locale> allLocales = Stream.of(new Object[][] {
                { "usa", Locale.US },
                { "france", Locale.FRANCE },
                {"germany", Locale.GERMANY},
                {"japan", Locale.JAPAN},
                {"italy", Locale.ITALY},
                {"korea", Locale.KOREA},
                {"china", Locale.CHINA},
                {"canada", Locale.CANADA},
                {"uk", Locale.UK}
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Locale) data[1]));
        Locale locale = Locale.US;
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);

        // Reading values from the console
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a locale: ");
        String localeString = scanner.nextLine();
        System.out.print("Enter a double: ");
        String priceString = scanner.nextLine();
        if (!localeString.isEmpty()) {
            if (!allLocales.containsKey(localeString.toLowerCase())) {
                throw new IllegalArgumentException("Incorrect input locale: " + locale);
            }
            locale = allLocales.get(localeString.toLowerCase());
            numberFormat = NumberFormat.getCurrencyInstance(locale);
        }

        // Output the received value
        try {
            BigDecimal price = new BigDecimal(priceString);
            System.out.println("Result: " + numberFormat.format(price));
        } catch (NumberFormatException e) {
            System.out.println("Can not convert string: " + priceString + " to double!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
