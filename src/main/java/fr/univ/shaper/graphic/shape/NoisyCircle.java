package fr.univ.shaper.graphic.shape;

import fr.univ.shaper.graphic.GraphicVisitor;

import java.awt.Color;

public class NoisyCircle extends Circle implements NoisyShape {

    private double noiseX;
    private double noiseY;

    public NoisyCircle(double cx, double cy, double radius, Color color) {
        super(cx, cy, radius, color);
    }

    public NoisyCircle(double cx, double cy, double radius, Color color, double noiseX, double noiseY) {
        super(cx, cy, radius, color);
        this.noiseX = noiseX;
        this.noiseY = noiseY;
    }

    @Override
    public double getNoiseX() {
        return noiseX;
    }

    @Override
    public void setNoiseX(double noiseX) {
        this.noiseX = noiseX;
    }

    @Override
    public double getNoiseY() {
        return noiseY;
    }

    @Override
    public void setNoiseY(double noiseY) {
        this.noiseY = noiseY;
    }

    @Override
    public void accept(GraphicVisitor visitor) {
        visitor.visitNoisyCircle(this);
    }
}
