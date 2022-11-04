package ru.croc.task4.figures;

import ru.croc.Task3.objs.Point;

// Describing a rectangle on a plane
public class Rectangle extends Figure {
    private Point leftDownPoint;
    private Point rightUpPoint;

    public Rectangle (Point leftDownPoint, Point rightUpPoint) {
        if (leftDownPoint.equals(rightUpPoint) || Double.compare(leftDownPoint.getX(), rightUpPoint.getX()) > 0 ||
        Double.compare(leftDownPoint.getY(), rightUpPoint.getY()) > 0) {
            throw new IllegalArgumentException("Incorrect Points data for the rectangle!");
        }
        this.leftDownPoint = leftDownPoint;
        this.rightUpPoint = rightUpPoint;
    }

    public Point getLeftDownPoint() {
        return this.leftDownPoint;
    }

    public void setLeftDownPoint(Point leftDownPoint) {
        this.leftDownPoint = leftDownPoint;
    }

    public Point getRightUpPoint() {
        return this.rightUpPoint;
    }

    public void setRightUpPoint(Point rightUpPoint) {
        this.rightUpPoint = rightUpPoint;
    }

    @Override
    public String toString() {
        return "Rectangle (" + this.leftDownPoint.getX() + "," + this.leftDownPoint.getY() + "), (" +
                this.rightUpPoint.getX() + ", " + this.rightUpPoint.getY() + ")";
    }
}
