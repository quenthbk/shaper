package fr.univ.shaper.graphic.shape;

import fr.univ.shaper.graphic.GraphicElement;

public interface NoisyShape extends GraphicElement {

    double getNoiseX();

    double getNoiseY();

    void setNoiseX(double noise);

    void setNoiseY(double noise);
}
