package fr.univ.shaper.graphic;

import fr.univ.shaper.graphic.shape.Circle;
import fr.univ.shaper.graphic.shape.Line;
import fr.univ.shaper.graphic.shape.Rectangle;

import java.awt.*;

public interface GraphicFactory {
    Layer createLayer();

    Rectangle createRectangle(double x0, double y0, double x1, double y1, Color color);

    Circle createCircle(double cx, double cy, double radius, Color color);

    Line createLine(double x0, double y0, double x1, double y1, Color color);
}
