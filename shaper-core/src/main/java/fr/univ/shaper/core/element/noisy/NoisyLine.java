package fr.univ.shaper.core.element.noisy;

import fr.univ.shaper.core.GraphicVisitor;
import fr.univ.shaper.core.element.Line;

import java.awt.Color;
import java.util.Objects;

public class NoisyLine extends Line implements Noisy {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NoisyLine)) return false;
        NoisyLine that = (NoisyLine) o;
        return super.equals(that) &&
                Double.compare(that.getNoiseX(), getNoiseX()) == 0 &&
                Double.compare(that.getNoiseY(), getNoiseY()) == 0;
    }

    @Override
    public boolean canEqual(Object o) {
        return o instanceof NoisyLine;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getNoiseX(), getNoiseY());
    }

    @Override
    public String toString() {
        return "NoisyLine{" +
                "x0=" + getX0() +
                ", y0=" + getY0() +
                ", x1=" + getX1() +
                ", y1=" + getY1() +
                ", color=" + getColor() +
                ", noiseX=" + getNoiseX() +
                ", noiseY=" + getNoiseY() +
                '}';
    }
}
