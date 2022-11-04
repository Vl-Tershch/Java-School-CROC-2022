package ru.croc.task4;

import ru.croc.Task3.objs.Point;
import ru.croc.task4.figures.Figure;
import ru.croc.task4.figures.Rectangle;
import ru.croc.task4.figures.Circle;
import ru.croc.task4.annotations.Annotation;
import ru.croc.task4.annotations.AnnotatedImage;

public class Task4 {
    public static void main(String[] args) {
        System.out.println("[1] Rectangles:");
        Figure firstRect = new Rectangle(new Point(4, 2), new Point(10, 12));
        Figure secondRect = new Rectangle(new Point(10, 15), new Point(11, 16));
        Figure thirdRect = new Rectangle(new Point(230, 270), new Point(230, 280));
        System.out.println(firstRect);
        System.out.println(secondRect);
        System.out.println(thirdRect);

        System.out.println("\n[2] Circles:");
        Figure firstCirc = new Circle(new Point(1, 1), 21.0);
        Figure secondCirc = new Circle(new Point(47, 13), 10.0);
        Figure thirdCirc = new Circle(new Point(34, 25), 200.0);
        System.out.println(firstCirc);
        System.out.println(secondCirc);
        System.out.println(thirdCirc);

        System.out.println("\n[3] Annotations:");
        Annotation firstAnn = new Annotation("FirstRectangle", firstRect);
        Annotation secondAnn = new Annotation("SecondRectangle", secondRect);
        Annotation thirdAnn = new Annotation("ThirdRectangle", thirdRect);
        Annotation fourthAnn = new Annotation("FirstCircle", firstCirc);
        Annotation fifthAnn = new Annotation("SecondCircle", secondCirc);
        Annotation sixthAnn = new Annotation("ThirdCircle", thirdCirc);
        System.out.println(firstAnn);
        System.out.println(secondAnn);
        System.out.println(thirdAnn);
        System.out.println(fourthAnn);
        System.out.println(fifthAnn);
        System.out.println(sixthAnn);

        System.out.println("\n[4] Annotated Image:");
        Annotation[] allAnnotations = {firstAnn, secondAnn, thirdAnn, fourthAnn, fifthAnn, sixthAnn};
        AnnotatedImage annotatedImage = new AnnotatedImage("src/temps/images/myTest.jpg", allAnnotations);
        System.out.println(annotatedImage);
    }
}
