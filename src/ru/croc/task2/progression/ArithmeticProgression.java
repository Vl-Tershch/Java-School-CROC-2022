package ru.croc.task2.progression;

public class ArithmeticProgression {

    // A function that calculates the sum of an arithmetic progression
    public static double arithmeticProgressionAccumulate(double firstNumber, double difference, int count) {
        if (count == 0) {
            return 0;
        }
        double sumProgression = firstNumber, currentNumber = firstNumber;
        for (int i = 1; i < count; i++) {
            sumProgression += currentNumber + difference;
            currentNumber += difference;
        }
        return sumProgression;
    }
}
