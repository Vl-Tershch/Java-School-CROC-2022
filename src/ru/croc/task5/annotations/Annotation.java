package ru.croc.task5.annotations;

import ru.croc.task5.figures.Figure;

// Special class for markup data
public class Annotation {
    private final String description;
    private final Figure figure;

    public Annotation(String description, Figure figure) {
        this.description = description;
        this.figure = figure;
    }

    public Figure getFigure() {
        return this.figure;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return figure.toString() + ": " + description;
    }
}
