package fr.univ.shaper.graphic.element.perfect;

import fr.univ.shaper.graphic.GraphicVisitor;
import fr.univ.shaper.graphic.element.Circle;

import java.awt.Color;

public class PerfectCircle extends Circle {
    public PerfectCircle(double cx, double cy, double radius, Color color) {
        super(cx, cy, radius, color);
    }

    @Override
    public void accept(GraphicVisitor visitor) {
        visitor.visitPerfectCircle(this);
    }

    @Override
    public String toString() {
        return "PerfectCircle{" +
                "cx=" + getCx() +
                ", cy=" + getCy() +
                ", radius=" + getRadius() +
                ", color=" + getColor() +
                '}';
    }
}
