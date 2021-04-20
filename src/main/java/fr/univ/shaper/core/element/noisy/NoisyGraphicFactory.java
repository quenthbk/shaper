package fr.univ.shaper.core.element.noisy;

import fr.univ.shaper.core.GraphicFactory;
import fr.univ.shaper.core.element.*;
import fr.univ.shaper.core.noise.NoiseGraphicVisitor;

import java.awt.Color;

public class NoisyGraphicFactory implements GraphicFactory {

    private final NoiseGraphicVisitor visitor = new NoiseGraphicVisitor();

    private boolean generateNoise = false;

    public void setGenerateNoise(boolean value) {
        generateNoise = value;
    }

    @Override
    public Layer createLayer() {
        return new Layer();
    }

    @Override
    public Rectangle createRectangle(double x0, double y0, double x1, double y1, Color color) {
        Rectangle shape = new NoisyRectangle(x0, y0, x1, y1, color);
        makeNoise(shape);
        return shape;
    }

    @Override
    public Circle createCircle(double cx, double cy, double radius, Color color) {
        Circle shape = new NoisyCircle(cx, cy, radius, color);
        makeNoise(shape);
        return shape;
    }

    @Override
    public Line createLine(double x0, double y0, double x1, double y1, Color color) {
        Line shape = new NoisyLine(x0, y0, x1, y1, color);
        makeNoise(shape);
        return shape;
    }

    private void makeNoise(Shape shape) {
        if (generateNoise) {
            shape.accept(visitor);
        }
    }
}
