package fr.univ.shaper.graphic.shape;

import fr.univ.shaper.graphic.GraphicVisitor;

import java.awt.Color;

public class NoisyLine extends Line implements NoisyShape {

    private double noiseX;
    private double noiseY;

    public NoisyLine(double x0, double y0, double x1, double y1, Color color) {
        super(x0, y0, x1, y1, color);
    }

    public NoisyLine(double x0, double y0, double x1, double y1, Color color, double noiseX, double noiseY) {
        super(x0, y0, x1, y1, color);
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
        visitor.visitNoisyLine(this);
    }
}
