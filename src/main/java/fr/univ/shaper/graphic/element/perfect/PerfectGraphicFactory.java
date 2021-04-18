package fr.univ.shaper.graphic.element.perfect;

import fr.univ.shaper.graphic.GraphicFactory;
import fr.univ.shaper.graphic.element.Circle;
import fr.univ.shaper.graphic.element.Layer;
import fr.univ.shaper.graphic.element.Line;
import fr.univ.shaper.graphic.element.perfect.PerfectCircle;
import fr.univ.shaper.graphic.element.perfect.PerfectLine;
import fr.univ.shaper.graphic.element.perfect.PerfectRectangle;
import fr.univ.shaper.graphic.element.Rectangle;

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
