package fr.univ.shaper.graphic.element;

import java.awt.Color;
import java.util.Objects;

public abstract class Circle implements Shape {

    private double cx, cy, radius;
    private Color color;

    public Circle(double cx, double cy, double radius, Color color) {
        this.cx = cx;
        this.cy = cy;
        this.radius = radius;
        this.color = color;
    }

    public double getCx() {
        return cx;
    }

    public void setCx(double cx) {
        this.cx = cx;
    }

    public double getCy() {
        return cy;
    }

    public void setCy(double cy) {
        this.cy = cy;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Circle)) return false;
        Circle circle = (Circle) o;
        return canEqual(o) &&
                Double.compare(circle.getCx(), getCx()) == 0 &&
                Double.compare(circle.getCy(), getCy()) == 0 &&
                Double.compare(circle.getRadius(), getRadius()) == 0 &&
                Objects.equals(getColor(), circle.getColor());
    }

    protected boolean canEqual(Object o) {
        return o instanceof Circle;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCx(), getCy(), getRadius(), getColor());
    }

    @Override
    public String toString() {
        return "Circle{" +
                "cx=" + getCx() +
                ", cy=" + getCy() +
                ", radius=" + getRadius() +
                ", color=" + getColor() +
                '}';
    }
}
