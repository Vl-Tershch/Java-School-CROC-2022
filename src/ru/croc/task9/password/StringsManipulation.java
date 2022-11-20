package ru.croc.task9.password;

// Object casts
public class StringsManipulation {
    public static String getPasswordFromNumber(long number, PasswordConfig passwordConfig) {
        char[] chars = new char[passwordConfig.getPassLength()];
        for (int i = passwordConfig.getPassLength() - 1; i >= 0; i--) {
            chars[i] = (char) ((number % passwordConfig.getAlphabetLength()) + 'a');
            number /= passwordConfig.getAlphabetLength();
        }
        return String.valueOf(chars);
    }
}
