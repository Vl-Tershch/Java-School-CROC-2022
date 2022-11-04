package ru.croc.task4.figures;

import ru.croc.Task3.objs.Point;

// Describing a circle on a plane
public class Circle extends Figure {
    private Point centerPoint;
    private Double radius;

    public Circle(Point centerPoint, Double radius) {
        if (Double.compare(radius, 0.0) <= 0) {
            throw new IllegalArgumentException("Incorrect radius data for the circle!");
        }
        this.centerPoint = centerPoint;
        this.radius = radius;
    }

    public Point getCenterPoint() {
        return this.centerPoint;
    }

    public void setCenterPoint(Point centerPoint) {
        this.centerPoint = centerPoint;
    }

    public Double getRadius() {
        return this.radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Circle (" + centerPoint.getX() + "," + centerPoint.getY() + "), " + radius;
    }
}
