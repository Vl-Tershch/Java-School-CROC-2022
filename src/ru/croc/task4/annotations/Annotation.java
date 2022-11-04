package ru.croc.task4.annotations;

import ru.croc.task4.figures.Figure;

// Special class for markup data
public class Annotation {
    private final String description;
    private final Figure figure;

    public Annotation(String description, Figure figure) {
        this.description = description;
        this.figure = figure;
    }

    public Figure getFigure() {
        return figure;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return figure.toString() + ": " + description;
    }
}
