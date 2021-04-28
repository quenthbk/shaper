package fr.univ.shaper.core.element;

import fr.univ.shaper.util.Contract;

import java.awt.Color;

/**
 * Implémentation des méthodes de Shape
 */
abstract class AbstractShape implements Shape {

    private Color color;

    private Layer parent;

    public AbstractShape(Color color) {
        Contract.assertThat(color != null, "La couleur ne doit pas être null");
        this.color = color;
    }

    @Override
    public Layer getParent() {
        return parent;
    }

    @Override
    public void setParent(Layer parent) {
        this.parent = parent;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        Contract.assertThat(color != null, "La couleur ne doit pas être null");
        this.color = color;
    }
}
