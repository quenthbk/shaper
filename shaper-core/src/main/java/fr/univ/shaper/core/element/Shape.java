package fr.univ.shaper.core.element;

import java.awt.Color;

public interface Shape extends GraphicElement {
    /**
     * @return la couleur de la forme
     */
    Color getColor();

    /**
     * Modifie la couleur de la forme
     *
     * @param color la nouvelle couleur de la forme
     */
    void setColor(Color color);
}
