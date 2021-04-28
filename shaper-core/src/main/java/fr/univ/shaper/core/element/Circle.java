package fr.univ.shaper.core.element;

import java.awt.Color;
import java.util.Objects;

/**
 * Un Cercle Abstrait contenant l'implémentation des getter et setter
 */
public abstract class Circle extends AbstractShape {

    private double cx, cy, radius;

    public Circle(double cx, double cy, double radius, Color color) {
        super(color);
        this.cx = cx;
        this.cy = cy;
        this.radius = radius;
    }

    /**
     * La coordonnée X du centre
     *
     * @return coordonnée x du centre
     */
    public double getCx() {
        return cx;
    }

    /**
     * Modifie la coordonnée X du centre
     *
     * @param cx coordonnée x du centre
     */
    public void setCx(double cx) {
        this.cx = cx;
    }

    /**
     * La coordonnée Y du centre
     *
     * @return coordonnée Y du centre
     */
    public double getCy() {
        return cy;
    }

    /**
     * Modifie la coordonnée Y du centre
     *
     * @param cy coordonnée Y du centre
     */
    public void setCy(double cy) {
        this.cy = cy;
    }

    /**
     * Renvoie le rayon du cercle
     *
     * @return le rayon du cercle
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Modifie le rayon du cercle
     *
     * @param radius le rayon du cercle
     */
    public void setRadius(double radius) {
        this.radius = radius;
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
