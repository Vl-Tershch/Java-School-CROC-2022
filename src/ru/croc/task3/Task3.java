package ru.croc.task3;

import ru.croc.task3.objs.Point;
import ru.croc.task3.objs.Triangle;

public class Task3 {
    public static void main(String[] args) {
        if (args.length != 6) {
            throw new IllegalArgumentException("The number of arguments must be = 6!");
        }

        double[] inputCoordinates = new double[6];
        for (int i = 0; i < 6; i++) {
            inputCoordinates[i] = Double.parseDouble(args[i]);
        }
        Point[] trianglePoints = {new Point(inputCoordinates[0], inputCoordinates[1]),
                new Point(inputCoordinates[2], inputCoordinates[3]),
                new Point(inputCoordinates[4], inputCoordinates[5])};
        Triangle triangle = new Triangle(trianglePoints);

        // Rounding with precision 10^6
        double precision = Math.pow(10, 6);
        double resultArea = Math.ceil(triangle.getArea() * precision) / precision;
        String strResultArea = String.valueOf(resultArea);
        if (strResultArea.substring(strResultArea.indexOf ( "." )).equals(".0")) {
            System.out.println("Площадь треугольника: " + strResultArea.substring(0, strResultArea.indexOf ( "." )));
        } else {
            System.out.println("Площадь треугольника: " + strResultArea);
        }
    }
}
