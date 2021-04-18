package fr.univ.shaper.graphic.element.noisy;

import fr.univ.shaper.graphic.GraphicElement;

public interface Noisy extends GraphicElement {

    double getNoiseX();

    double getNoiseY();

    void setNoiseX(double noise);

    void setNoiseY(double noise);
}
