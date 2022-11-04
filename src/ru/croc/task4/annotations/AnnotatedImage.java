package ru.croc.task4.annotations;

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

    @Override
    public String toString() {
        return "AnnotatedImage (" +
                "imagePath='" + this.imagePath + '\'' +
                ", annotations=" + Arrays.toString(this.annotations) +
                ')';
    }
}
