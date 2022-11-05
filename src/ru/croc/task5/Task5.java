package ru.croc.task5;

import ru.croc.task5.annotations.AnnotatedImage;
import ru.croc.task5.annotations.Annotation;
import ru.croc.task5.figures.Circle;
import ru.croc.task5.figures.Point;
import ru.croc.task5.figures.Figure;
import ru.croc.task5.figures.Rectangle;

public class Task5 {
    public static void main(String[] args) {
        System.out.println("[1] Rectangles:");
        Figure firstRect = new Rectangle(new Point(4, 2), new Point(10, 12));
        Figure secondRect = new Rectangle(new Point(100, 150), new Point(110, 160));
        System.out.println(firstRect);
        System.out.println(secondRect);

        System.out.println("\n[2] Circles:");
        Figure firstCirc = new Circle(new Point(1, 1), 21.0);
        Figure secondCirc = new Circle(new Point(47, 13), 10.0);
        System.out.println(firstCirc);
        System.out.println(secondCirc);

        System.out.println("\n[3] Annotations:");
        Annotation firstAnn = new Annotation("FirstRectangle", firstRect);
        Annotation secondAnn = new Annotation("SecondRectangle", secondRect);
        Annotation thirdAnn = new Annotation("FirstCircle", firstCirc);
        Annotation fourthAnn = new Annotation("SecondCircle", secondCirc);
        System.out.println(firstAnn);
        System.out.println(secondAnn);
        System.out.println(thirdAnn);
        System.out.println(fourthAnn);

        System.out.println("\n[4] Annotated Image:");
        Annotation[] allAnnotations = {firstAnn, secondAnn, thirdAnn, fourthAnn};
        AnnotatedImage annotatedImage = new AnnotatedImage("src/temps/images/myTest.jpg", allAnnotations);
        System.out.println(annotatedImage);

        System.out.println("\n[5] New functions:");
        System.out.println("Find by point and moving!");
        Annotation annTestPoint = annotatedImage.findByPoint(100, 150);
        if (annTestPoint != null) {
            System.out.println("Current annotation -> " + annTestPoint);
            Figure currentFigure = annTestPoint.getFigure();
            System.out.println("Current figure -> " + currentFigure);
            currentFigure.move(2, 2);
            System.out.println("First moving + (2, 2) -> " + currentFigure);
            currentFigure.move(-4, -3);
            System.out.println("Second moving + (-4, -3) -> " + currentFigure);
        }

        System.out.println("\nFind by label and moving!");
        Annotation annTestLabel = annotatedImage.findByLabel("FirstCircle");
        if (annTestLabel != null) {
            System.out.println("Current annotation -> " + annTestLabel);
            Figure currentFigure = annTestLabel.getFigure();
            System.out.println("Current figure -> " + currentFigure);
            currentFigure.move(10, 0);
            System.out.println("First moving + (10, 0) -> " + currentFigure);
            currentFigure.move(-40, 10);
            System.out.println("Second moving + (-40, 10) -> " + currentFigure);
        }
    }
}
