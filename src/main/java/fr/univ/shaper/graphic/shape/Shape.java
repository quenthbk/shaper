package fr.univ.shaper.graphic.shape;

import fr.univ.shaper.graphic.GraphicElement;

import java.awt.Color;

public abstract class Shape implements GraphicElement {

    private Color color;

    Shape(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
