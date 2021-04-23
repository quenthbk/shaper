package fr.univ.shaper.core.element.noisy;

import fr.univ.shaper.core.element.GraphicElement;

public interface Noisy extends GraphicElement {

    double getNoiseX();

    double getNoiseY();

    void setNoiseX(double noise);

    void setNoiseY(double noise);
}
