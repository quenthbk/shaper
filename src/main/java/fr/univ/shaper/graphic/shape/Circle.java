package fr.univ.shaper.graphic.shape;

import fr.univ.shaper.graphic.GraphicVisitor;

public abstract class Circle extends Shape {

    private double cx, cy, radius;

    public Circle(double cx, double cy, double radius) {

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
    public void accept(GraphicVisitor visitor) {
        visitor.visitCircle(this);
    }
}
