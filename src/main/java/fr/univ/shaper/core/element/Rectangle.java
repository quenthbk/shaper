package fr.univ.shaper.core.element;

import java.awt.Color;
import java.util.Objects;

public abstract class Rectangle implements Shape {

    private double x0, y0, x1, y1;
    private Color color;

    public Rectangle(double x0, double y0, double x1, double y1, Color color) {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
        this.color = color;
    }

    public double getX0() {
        return x0;
    }

    public void setX0(double x0) {
        this.x0 = x0;
    }

    public double getY0() {
        return y0;
    }

    public void setY0(double y0) {
        this.y0 = y0;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getY1() {
        return y1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public double getWidth() { return Math.abs(x1-x0); }

    public double getHeight() { return Math.abs(y1-y0); }

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
        if (!(o instanceof Rectangle)) return false;
        Rectangle rectangle = (Rectangle) o;
        return canEqual(o) &&
                Double.compare(rectangle.getX0(), getX0()) == 0 &&
                Double.compare(rectangle.getY0(), getY0()) == 0 &&
                Double.compare(rectangle.getX1(), getX1()) == 0 &&
                Double.compare(rectangle.getY1(), getY1()) == 0 &&
                Objects.equals(getColor(), rectangle.getColor());
    }

    protected boolean canEqual(Object o) {
        return o instanceof Rectangle;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX0(), getY0(), getX1(), getY1(), getColor());
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "x0=" + getX0() +
                ", y0=" + getY0() +
                ", x1=" + getX1() +
                ", y1=" + getY1() +
                ", color=" + getColor() +
                '}';
    }
}