package fr.univ.shaper.graphic.element;

import fr.univ.shaper.graphic.GraphicElement;

import java.awt.Color;

public interface Shape extends GraphicElement {
    Color getColor();
    void setColor(Color color);
}
