package ru.croc.Task2;
import ru.croc.Task2.progression.ArithmeticProgression;

public class Task2 {
    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("The number of arguments must be = 3!");
        }

        double firstArg = Double.parseDouble(args[0]), secondArg = Double.parseDouble(args[1]);
        int thirdArg = Integer.parseInt(args[2]);
        double resultAccum = ArithmeticProgression.arithmeticProgressionAccumulate(firstArg, secondArg, thirdArg);

        String strResultAccum = String.valueOf(resultAccum);
        if (strResultAccum.substring(strResultAccum.indexOf ( "." )).equals(".0")) {
            System.out.println("Sum: " + strResultAccum.substring(0, strResultAccum.indexOf ( "." )));
        } else {
            System.out.println("Sum: " + resultAccum);
        }
    }
}
