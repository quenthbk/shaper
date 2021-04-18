package fr.univ.shaper.graphic.element.perfect;

import fr.univ.shaper.graphic.GraphicVisitor;
import fr.univ.shaper.graphic.element.Line;

import java.awt.Color;

public class PerfectLine extends Line {
    public PerfectLine(double x0, double y0, double x1, double y1, Color color) {
        super(x0, y0, x1, y1, color);
    }
    @Override
    public void accept(GraphicVisitor visitor) {
        visitor.visitPerfectLine(this);
    }

    @Override
    public String toString() {
        return "PerfectLine{" +
                "x0=" + getX0() +
                ", y0=" + getY0() +
                ", x1=" + getX1() +
                ", y1=" + getY1() +
                ", color=" + getColor() +
                '}';
    }
}
