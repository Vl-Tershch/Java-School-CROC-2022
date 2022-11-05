package ru.croc.task5.figures;

import ru.croc.task5.interfaces.Movable;

// Describing a figure on a plane
public abstract class Figure implements Movable {
    // Special check for the presence of a point inside a figure
    public abstract boolean contains(Point point);
}
