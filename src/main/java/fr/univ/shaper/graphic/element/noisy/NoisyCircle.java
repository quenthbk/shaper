package fr.univ.shaper.graphic.element.noisy;

import fr.univ.shaper.graphic.GraphicVisitor;
import fr.univ.shaper.graphic.element.Circle;

import java.awt.Color;
import java.util.Objects;

public class NoisyCircle extends Circle implements Noisy {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NoisyCircle)) return false;
        NoisyCircle that = (NoisyCircle) o;
        return super.equals(that) &&
                Double.compare(that.getNoiseX(), getNoiseX()) == 0 &&
                Double.compare(that.getNoiseY(), getNoiseY()) == 0;
    }

    @Override
    public boolean canEqual(Object o) {
        return o instanceof NoisyCircle;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getNoiseX(), getNoiseY());
    }

    @Override
    public String toString() {
        return "NoisyCircle{" +
                "cx=" + getCx() +
                ", cy=" + getCy() +
                ", radius=" + getRadius() +
                ", color=" + getColor() +
                ", noiseX=" + getNoiseX() +
                ", noiseY=" + getNoiseY() +
                '}';
    }
}
