package ru.croc.task5.figures;

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

    @Override
    public void move(int dx, int dy) {
        this.leftDownPoint.move(dx, dy);
        this.rightUpPoint.move(dx, dy);
    }

    @Override
    public boolean contains(Point point) {
        return Double.compare(this.leftDownPoint.getX(), point.getX()) <= 0 &&
                Double.compare(this.leftDownPoint.getY(), point.getY()) <= 0 &&
                Double.compare(this.rightUpPoint.getX(), point.getX()) >= 0 &&
                Double.compare(this.rightUpPoint.getY(), point.getY()) >= 0;
    }
}