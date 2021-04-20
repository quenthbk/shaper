package fr.univ.shaper.core.element;

import fr.univ.shaper.core.GraphicElement;

import java.awt.Color;

public interface Shape extends GraphicElement {
    Color getColor();
    void setColor(Color color);
}
