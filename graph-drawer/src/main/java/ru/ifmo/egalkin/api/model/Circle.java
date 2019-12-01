package ru.ifmo.egalkin.api.model;

public class Circle {
    private Point rectangleLeftCornen;
    private int radius;

    public Circle(Point center, int radius) {
        this.rectangleLeftCornen = new Point(center.x - radius, center.y - radius);
        this.radius = radius;
    }

    public Point getRectangleLeftCornen() {
        return rectangleLeftCornen;
    }

    public int getRadius() {
        return radius;
    }
}
