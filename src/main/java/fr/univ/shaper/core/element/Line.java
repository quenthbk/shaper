package fr.univ.shaper.core.element;

import java.awt.Color;
import java.util.Objects;

public abstract class Line extends AbstractShape {

    double x0, y0, x1, y1;

    public Line(double x0, double y0, double x1, double y1, Color color) {
        super(color);
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
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

    public double getLength() {
        double dx=Math.abs(x1-x0);
        double dy=Math.abs(y1-y0);
        return Math.sqrt(dx*dx+dy*dy);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Line)) return false;
        Line line = (Line) o;
        return canEqual(o) &&
                Double.compare(line.getX0(), getX0()) == 0 &&
                Double.compare(line.getY0(), getY0()) == 0 &&
                Double.compare(line.getX1(), getX1()) == 0 &&
                Double.compare(line.getY1(), getY1()) == 0 &&
                Objects.equals(getColor(), line.getColor());
    }

    protected boolean canEqual(Object o) {
        return o instanceof Line;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX0(), getY0(), getX1(), getY1(), getColor());
    }

    @Override
    public String toString() {
        return "Line{" +
                "x0=" + getX0() +
                ", y0=" + getY0() +
                ", x1=" + getX1() +
                ", y1=" + getY1() +
                ", color=" + getColor() +
                '}';
    }
}
