package fr.univ.shaper.core.element.perfect;

import fr.univ.shaper.core.element.GraphicFactory;
import fr.univ.shaper.core.element.Circle;
import fr.univ.shaper.core.element.Layer;
import fr.univ.shaper.core.element.Line;
import fr.univ.shaper.core.element.Rectangle;

import java.awt.*;

public class PerfectGraphicFactory implements GraphicFactory {

    @Override
    public Layer createLayer() {
        return new Layer();
    }

    @Override
    public Rectangle createRectangle(double x0, double y0, double x1, double y1, Color color) {
        return new PerfectRectangle(x0, y0, x1, y1, color);
    }

    @Override
    public Circle createCircle(double cx, double cy, double radius, Color color) {
        return new PerfectCircle(cx, cy, radius, color);
    }

    @Override
    public Line createLine(double x0, double y0, double x1, double y1, Color color) {
        return new PerfectLine(x0, y0, x1, y1, color);
    }
}
