package fr.univ.shaper.graphic.shape;

import fr.univ.shaper.graphic.GraphicVisitor;

import java.awt.Color;

public class PerfectCircle extends Circle {
    public PerfectCircle(double cx, double cy, double radius, Color color) {
        super(cx, cy, radius, color);
    }

    @Override
    public void accept(GraphicVisitor visitor) {
        visitor.visitPerfectCircle(this);
    }
}
