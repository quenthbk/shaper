package fr.univ.shaper.core.element.perfect;

import fr.univ.shaper.core.GraphicVisitor;
import fr.univ.shaper.core.element.Rectangle;

import java.awt.Color;

public class PerfectRectangle extends Rectangle {
    public PerfectRectangle(double x0, double y0, double x1, double y1, Color color) {
        super(x0, y0, x1, y1, color);
    }

    @Override
    public void accept(GraphicVisitor visitor) {
        visitor.visitPerfectRectangle(this);
    }

    @Override
    public String toString() {
        return "PerfectRectangle{" +
                "x0=" + getX0() +
                ", y0=" + getY0() +
                ", x1=" + getX1() +
                ", y1=" + getY1() +
                ", color=" + getColor() +
                '}';
    }
}
