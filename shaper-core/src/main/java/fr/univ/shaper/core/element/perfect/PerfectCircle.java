package fr.univ.shaper.core.element.perfect;

import fr.univ.shaper.core.GraphicVisitor;
import fr.univ.shaper.core.element.Circle;

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
