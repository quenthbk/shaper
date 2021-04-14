package fr.univ.shaper.graphic;

import fr.univ.shaper.graphic.shape.Circle;
import fr.univ.shaper.graphic.shape.Line;
import fr.univ.shaper.graphic.shape.Rectangle;

public class DefaultGraphicFactory implements GraphicFactory {

    @Override
    public Layer createLayer() {
        return new Layer();
    }

    @Override
    public Rectangle createRectangle(double x0, double y0, double x1, double y1) {
        return new Rectangle(x0, y0, x1, y1) {};
    }

    @Override
    public Circle createCircle(double cx, double cy, double radius) {
        return new Circle(cx, cy, radius) {};
    }

    @Override
    public Line createLine(double x0, double y0, double x1, double y1) {
        return new Line(x0, y0, x1, y1) {};
    }
}
