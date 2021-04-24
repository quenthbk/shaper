package fr.univ.shaper.core.element;

import java.awt.Color;

public interface Shape extends GraphicElement {
    Color getColor();

    void setColor(Color color);
}
