package ru.croc.task5.annotations;

import ru.croc.task5.figures.Point;
import java.util.Arrays;

// Special class for annotated image
public class AnnotatedImage {
    private final String imagePath;
    private final Annotation[] annotations;

    public AnnotatedImage(String imagePath, Annotation... annotations) {
        this.imagePath = imagePath;
        this.annotations = annotations;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public Annotation[] getAnnotations() {
        return this.annotations;
    }

    // Annotation selection by point coordinates (x, y)
    public Annotation findByPoint(int x, int y) {
        Point findPoint = new Point(x, y);
        for (Annotation ann : this.annotations) {
            if (ann.getFigure().contains(findPoint)) {
                return ann;
            }
        }
        return null;
    }

    // Annotation selection by label
    public Annotation findByLabel(String label) {
        for (Annotation ann : this.annotations) {
            if (ann.getDescription().contains(label)) {
                return ann;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "AnnotatedImage (" +
                "imagePath='" + this.imagePath + '\'' +
                ", annotations=" + Arrays.toString(this.annotations) +
                ')';
    }
}
