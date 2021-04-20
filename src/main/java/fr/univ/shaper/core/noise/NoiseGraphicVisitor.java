package fr.univ.shaper.core.noise;

import fr.univ.shaper.core.GraphicVisitorAdapter;
import fr.univ.shaper.core.element.Layer;
import fr.univ.shaper.core.element.noisy.NoisyCircle;
import fr.univ.shaper.core.element.noisy.NoisyLine;
import fr.univ.shaper.core.element.noisy.NoisyRectangle;
import fr.univ.shaper.core.element.noisy.Noisy;

public class NoiseGraphicVisitor extends GraphicVisitorAdapter {

    private final Double noiseX;
    private final Double noiseY;

    public NoiseGraphicVisitor(double noiseX, double noiseY) {
        this.noiseX = noiseX;
        this.noiseY = noiseY;
    }

    public NoiseGraphicVisitor() {
        noiseX = null;
        noiseY = null;
    }

    @Override
    public void visitLayer(Layer element) {
        element.getChildren().forEach(child -> child.accept(this));
    }

    @Override
    public void visitNoisyRectangle(NoisyRectangle element) {
        if (noiseX != null && noiseY != null) {
            setNoise(element);
            return;
        }

        element.setX0(element.getX0() + Noise.getNoise());
        element.setY0(element.getY0() + Noise.getNoise());
        element.setX1(element.getX1() + Noise.getNoise());
        element.setY1(element.getY1() + Noise.getNoise());
        element.setNoiseX(Noise.getNoise(element.getWidth()));
        element.setNoiseY(Noise.getNoise(element.getHeight()));
    }

    @Override
    public void visitNoisyCircle(NoisyCircle element) {
        if (noiseX != null && noiseY != null) {
            setNoise(element);
            return;
        }

        element.setCx(element.getCx() + Noise.getNoise());
        element.setCy(element.getCy() + Noise.getNoise());
        element.setNoiseX(Noise.getNoise(element.getRadius()));
        element.setNoiseY(Noise.getNoise(element.getRadius()));
    }

    @Override
    public void visitNoisyLine(NoisyLine element) {
        if (noiseX != null && noiseY != null) {
            setNoise(element);
            return;
        }

        element.setX0(element.getX0() + Noise.getNoise());
        element.setY0(element.getY0() + Noise.getNoise());
        element.setX1(element.getX1() + Noise.getNoise());
        element.setY1(element.getY1() + Noise.getNoise());
        element.setNoiseX(Noise.getNoise(element.getLength()));
        element.setNoiseY(Noise.getNoise(element.getLength()));
    }

    private void setNoise(Noisy shape) {
        assert noiseX != null;
        assert noiseY != null;
        shape.setNoiseX(noiseX);
        shape.setNoiseY(noiseY);
    }
}
