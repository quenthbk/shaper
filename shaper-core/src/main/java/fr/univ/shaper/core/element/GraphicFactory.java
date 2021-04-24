package fr.univ.shaper.core.element;

import java.awt.Color;

public interface GraphicFactory {
    Layer createLayer();

    Rectangle createRectangle(double x0, double y0, double x1, double y1, Color color);

    Circle createCircle(double cx, double cy, double radius, Color color);

    Line createLine(double x0, double y0, double x1, double y1, Color color);
}
