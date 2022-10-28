package ru.croc.Task3.objs;

// Describing a triangle on a plane
public class Triangle {
    private Point[] points;

    public Triangle(Point[] points) {
        checkExistenceTriangle(points[0], points[1], points[2]);
        this.points = points;
    }

    public Point[] getPoints() {
        return points;
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }

    // Calculates a side by two points
    public double calculateSide(Point p1, Point p2) {
        return Math.sqrt(Math.pow((p1.getX()) - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }

    // Calculates the area of a triangle
    public double getArea() {
        double firstSide = calculateSide(this.points[0], this.points[1]);
        double secondSide = calculateSide(this.points[0], this.points[2]);
        double thirdSide = calculateSide(this.points[1], this.points[2]);
        double halfPerimeter = (firstSide + secondSide + thirdSide) / 2.0;

        return Math.sqrt(halfPerimeter * (halfPerimeter - firstSide) * (halfPerimeter - secondSide)
                * (halfPerimeter - thirdSide));
    }

    // Checks whether a triangle with specified points can exist
    private void checkExistenceTriangle(Point p1, Point p2, Point p3) {
        double firstSide = calculateSide(p1, p2);
        double secondSide = calculateSide(p1, p3);
        double thirdSide = calculateSide(p2, p3);
        if (firstSide >= secondSide + thirdSide || secondSide >= firstSide + thirdSide
                || thirdSide >= firstSide + secondSide) {
            throw new IllegalArgumentException("A triangle with such sides does not exist!");
        }
    }
}
