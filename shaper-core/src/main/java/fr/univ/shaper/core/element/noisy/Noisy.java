package fr.univ.shaper.core.element.noisy;

import fr.univ.shaper.core.element.GraphicElement;

/**
 * Interface contenant les méthodes a implémenter pour un élément graphique
 *  de type Noisy.
 */
public interface Noisy extends GraphicElement {

    /**
     * @return la valeur du bruit en X
     */
    double getNoiseX();

    /**
     * @return la valeur du bruit en Y
     */
    double getNoiseY();

    /**
     * Modifie la valeur du bruit en X
     *
     * @param noise la valeur du bruit à changer
     */
    void setNoiseX(double noise);

    /**
     * Modifie la valeur du bruit en Y
     *
     * @param noise la valeur du bruit à changer
     */
    void setNoiseY(double noise);
}
