package fr.univ.shaper.core.element;

import java.awt.Color;
import java.util.Objects;

public abstract class Rectangle extends AbstractShape {

    private double x0, y0, x1, y1;

    public Rectangle(double x0, double y0, double x1, double y1, Color color) {
        super(color);
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
    }

    /**
     * @return la coordonnée x du point de début
     */
    public double getX0() {
        return x0;
    }

    /**
     * Modifie la coordonnée x du point de début
     *
     * @param x0 coordonnée x du point de début
     */
    public void setX0(double x0) {
        this.x0 = x0;
    }

    /**
     * @return la coordonnée y du point de début
     */
    public double getY0() {
        return y0;
    }

    /**
     * Modifie la coordonnée y du point de début
     *
     * @param y0 coordonnée y du point de début
     */
    public void setY0(double y0) {
        this.y0 = y0;
    }

    /**
     * @return la coordonnée x du point de fin
     */
    public double getX1() {
        return x1;
    }

    /**
     * Modifie la coordonnée x du point de fin
     *
     * @param x1 coordonnée x du point de fin
     */
    public void setX1(double x1) {
        this.x1 = x1;
    }

    /**
     * @return la coordonnée y du point de fin
     */
    public double getY1() {
        return y1;
    }

    /**
     * Modifie la coordonnée y du point de fin
     *
     * @param y1 coordonnée y du point de fin
     */
    public void setY1(double y1) {
        this.y1 = y1;
    }

    /**
     * Calcule et renvoie longueur du rectangle
     *
     * @return la longueur du rectangle
     */
    public double getWidth() { return Math.abs(x1-x0); }

    /**
     * Calcule et renvoie hauteur du rectangle
     *
     * @return la hauteur du rectangle
     */
    public double getHeight() { return Math.abs(y1-y0); }

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